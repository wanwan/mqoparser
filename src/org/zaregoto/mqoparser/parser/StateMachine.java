package org.zaregoto.mqoparser.parser;

import org.zaregoto.mqoparser.Settings;
import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.exception.LoadStateException;
import org.zaregoto.mqoparser.exception.StateTransferException;
import org.zaregoto.mqoparser.util.LogUtil;

import javax.tools.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StateMachine {

    private static final String STATE_TRANSFER_TABLE = "/org/zaregoto/mqoparser/parser/tbl/states_tbl.csv";
    //private static final java.lang.String STATE_PACKAGE_ROOT = "org.zaregoto.mqoparser.parser.states";
    private static final String INITIAL_STATE_NAME = "Idle";
    private static final String[] STATE_PACKAGES = {
            "org.zaregoto.mqoparser.parser.states",
            "org.zaregoto.mqoparser.parser.states.header",
            "org.zaregoto.mqoparser.parser.states.material",
            "org.zaregoto.mqoparser.parser.states.object",
            "org.zaregoto.mqoparser.parser.states.object.bvertex",
            "org.zaregoto.mqoparser.parser.states.object.face",
            "org.zaregoto.mqoparser.parser.states.object.vertexattr",
            "org.zaregoto.mqoparser.parser.states.scene",
    };

    private State current;
    private Stack<Object> stack;

    private MQOData mqodata;
    private HashMap<State, HashMap<LexicalElement, State>> transferTable;
    private HashMap<String, State> statesTable;


    public StateMachine() {
        stack = new Stack<Object>();
        mqodata = new MQOData();

        statesTable = new HashMap<String, State>();
        transferTable = new HashMap<State, HashMap<LexicalElement, State>>();
    }


    public synchronized void push(Object obj) {

        stack.push(obj);
        if (Settings.USE_LOG) {
            StringBuffer sb = new StringBuffer();

            sb.append("+++++ statemachine stack: push : " + stack.size() + " ");
            Iterator<Object> it = stack.iterator();
            while (it.hasNext()) {
                Object element = it.next();
                sb.append(element.getClass().getName() + " ");
            }
            LogUtil.d(sb.toString());
        }
    }

    public synchronized Object pop() {

        if (Settings.USE_LOG) {
            StringBuffer sb = new StringBuffer();

            sb.append("----- statemachine stack: pop : " + (stack.size() - 1) + " ");
            Iterator<Object> it = stack.iterator();
            while (it.hasNext()) {
                Object element = it.next();
                sb.append(element.getClass().getName() + " ");
            }
            LogUtil.d(sb.toString());
        }
        return stack.pop();
    }


    public void initialize() throws LoadStateException, StateTransferException {

        InputStream is = null;
        BufferedReader br;
        String hdr;
        String[] inputs;
        String line;
        State curState;
        State nextState;
        String[] states;

        try {
            searchStateClass(statesTable);

            current = statesTable.get(INITIAL_STATE_NAME);
            if (!current.preTransfer(this, LexicalElement.NOP)) {
                throw new StateTransferException("init status before return false");
            }

            is = StateMachine.class.getResourceAsStream(STATE_TRANSFER_TABLE);

            if (null != is) {
                br = new BufferedReader(new InputStreamReader(is));

                if (null != br) {

                    try {
                        //br.reset();
                        hdr = br.readLine();
                        if (null != hdr) {
                            inputs = hdr.split(",");
                            if (inputs.length < 2) {
                                throw new LoadStateException("header size is too small, maybe format is not correct: " + STATE_TRANSFER_TABLE);
                            }
                        }
                        else {
                            throw new LoadStateException("header is null, maybe format is not correct: " + STATE_TRANSFER_TABLE);
                        }


                        while (null != (line = br.readLine())) {
                            states = line.split(",");
                            if (inputs.length != states.length) {
                                throw new LoadStateException("state length and input length is not same, maybe format is not correct: " + STATE_TRANSFER_TABLE);
                            }
                            else {
                                curState = statesTable.get(states[0]);
                                if (null == transferTable.get(curState)) {
                                    transferTable.put(curState, new HashMap<LexicalElement, State>());
                                }
                                for (int i = 1; i < inputs.length; i++) {
                                    nextState = statesTable.get(states[i]);
                                    HashMap<LexicalElement, State> tbl = transferTable.get(curState);
                                    tbl.put(LexicalElement.valueOf(inputs[i]), nextState);
                                }
                            }
                        }

                    } catch (IOException e) {
                        throw new LoadStateException("cannot read STATE_TRANSFER_TABLE: " + STATE_TRANSFER_TABLE);
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    throw new LoadStateException("cannot read STATE_TRANSFER_TABLE(line): " + STATE_TRANSFER_TABLE);
                }
            }
            else {
                throw new LoadStateException("cannot load STATE_TRANSFER_TABLE: " + STATE_TRANSFER_TABLE);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try { is.close();  } catch (IOException e) { e.printStackTrace();  }
            }
        }
    }

    private void searchStateClass(HashMap<String, State> availableStates) throws NoSuchFieldException, IllegalAccessException, InstantiationException, IOException {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager fm = compiler.getStandardFileManager(new DiagnosticCollector<JavaFileObject>(), null, null);
        Set<JavaFileObject.Kind> kind = new HashSet<JavaFileObject.Kind>(){{
            add(JavaFileObject.Kind.CLASS);
        }};

        if (null == availableStates) {
            return;
        }

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String regex = ".*/(.*)\\.class$";
        Pattern ptn = Pattern.compile(regex);
        String className;

        for (String packageName : STATE_PACKAGES) {
            for (JavaFileObject f : fm.list(StandardLocation.CLASS_PATH, packageName, kind, false)) {
                Matcher m = ptn.matcher(f.getName());
                if (m.find()) {
                    try {
                        className = m.group(1);
                        if (!className.contains("$")) { // ignore inner class case
                            State state = (State) loader.loadClass(packageName + "." + className).newInstance();
                            availableStates.put(className, state);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void transfer(final LexicalElement input) throws StateTransferException {

        State nextState;

        nextState = transferTable.get(current).get(input);

        if (!current.equals(nextState)) {

            if (null != current && current.postTransfer(this, input)) {
                if (null != nextState && nextState.preTransfer(this, input)) {
                    LogUtil.d("StateMachine.transfer current: " + current.getStateName() + " input: " + input + " next: " + nextState.getStateName());
                    current = nextState;
                } else {
                    throw new StateTransferException("state transfer failed, next status preTransfer() routine return error. current: " + current + " input: " + input);
                }
            } else {
                throw new StateTransferException("state transfer failed, current status postTransfer() routine return error. current: " + current + " input: " + input);
            }
        } else {
            current.received(this, input);
        }

    }



    public MQOData getMqodata() {
        return mqodata;
    }

    public void setMqodata(MQOData mqodata) {
        this.mqodata = mqodata;
    }


}
