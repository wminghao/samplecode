package switchinterview;

//create a simple calculator, no parenthesis, + * /
//better to use 2 stacks.
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String input = "2*3/3+5/6*3+15*2/3+33";
        double result = calc(input);
        System.out.println("Result=" + result);
    }

    public static final int NOO = 0;
    public static final int ADD = 1;
    public static final int MUL = 2;
    public static final int DIV = 3;

    private static interface Obj {
        public double getResult();
        public void setA(int a);
        public void setB(int b);
        public void setA(Obj a);
        public void setB(Obj b);
    }

    private static class Value implements Obj {
        public int a;
        public Value(int a) {
            this.a = a;
        }

        public void setA(int a) {}
        public void setB(int b) {}
        public void setA(Obj a) {}
        public void setB(Obj b) {}
        public double getResult() {
            return (double)a;
        }
    }
    private static class Tuple implements Obj {
        public Obj a;
        public Obj b;
        public int op;
        public Tuple(int op) {
            this.op = op;
        }

        public void setA(int a) {
            this.a = new Value(a);
        }

        public void setA(Obj a) {
            this.a = a;
        }

        public void setB(int b) {
            this.b = new Value(b);
        }

        public void setB(Obj b) {
            this.b = b;
        }

        public double getResult() {
            double aRes = a.getResult();
            double bRes = b.getResult();
            switch(op) {
                case ADD: {
                    System.out.println(aRes+"+"+bRes);
                    return aRes + bRes;
                }
                case MUL: {
                    System.out.println(aRes+"*"+bRes);
                    return aRes * bRes;
                }
                case DIV: {
                    System.out.println(aRes+"/"+bRes);
                    return aRes / bRes;
                }
                default: {
                    //assert(0);
                    return 0;
                }
            }
        }
    }

    public static double calc(String input) {
        int len = input.length();
        int val = 0;
        Obj result = null;
        Obj prev = null;
        int prevOp = NOO;
        Obj curPlus = null;
        for(int i = 0; i<len; i++) {
            char cur = input.charAt(i);
            if( cur>='0' && cur <='9' ) {
                val = val*10 + (cur-'0');
            } else if ( cur == '*' || cur == '/') {
                int curOp = cur == '*'? MUL:DIV;

                System.out.println(cur+" val=" + val);
                Obj now = new Tuple(curOp);
                if( prev != null ) {
                    if( prevOp == MUL || prevOp == DIV) {
                        prev.setB(val);
                        now.setA(prev);
                        System.out.println("prevOp = mul/div");
                        if( curPlus != null ) {
                            System.out.println("curPlus exist");
                            curPlus.setB(now);
                        } else {
                            System.out.println("curPlus not exist");
                        }
                    } else {
                        System.out.println("prevOp = plus");
                        prev.setB(now);
                        now.setA(val);
                    }
                } else {
                    System.out.println("prev no prev");
                    now.setA(val);
                }
                prev = now;
                if( curPlus == null ) {
                    result = now;
                }
                prevOp = MUL;
                val = 0;
            } else if ( cur == '+' ) {
                System.out.println("+ val=" + val);
                Obj now = new Tuple(ADD);
                if( prev != null ) {
                    prev.setB(val);
                    if( curPlus != null ) {
                        System.out.println("+ curPlus exist");
                        now.setA(curPlus);
                    } else {
                        System.out.println("+ curPlus not exist");
                        now.setA(prev);
                    }
                } else {
                    System.out.println("prev no prev");
                    now.setA(val);
                }
                prev = now;
                result = now;
                prevOp = ADD;
                curPlus = now;
                val = 0;
            }
        }
        if( prev != null ) {
            prev.setB(val);
        }
        return result!=null? result.getResult(): 0;
    }

}