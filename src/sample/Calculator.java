package sample;

import java.util.Stack;

public class Calculator {


    public static Object getResult(String s) throws Exception {
        String result[]=seperate(s);
        double resultOperation= operation(result);

        if((resultOperation-(int)resultOperation)==0)
            return (long)resultOperation;
        else
            return resultOperation;

    }

    private static String[] seperate(String str) throws Exception{
        String result="";
        for (int i=0 ; i<str.length();i++) {
            switch (str.charAt(i)) {
                case '+':
                case '*':
                case '/':
                case '-':
                    result+=" "+str.charAt(i)+" ";
                    break;
                case '(' :
                    if(result.length()>0 && Character.isDigit(result.charAt(result.length()-1) ) )
                        result+=" * ( ";
                    else if( result.length()>1 && result.charAt(result.length()-2) ==')' )
                        result+=" * ( ";
                    else
                        result+=" ( ";
                    break;
                case ')' :
                    if(i<str.length()-2&&Character.isDigit(str.charAt(i+1)))
                        throw new Exception("error");
                    result+=" "+str.charAt(i)+" ";
                    break;
                default:
                    result+=str.charAt(i);
                    break;
            }
        }
        return result.split(" ");
    }

    /***********************************/

    private static Double operation(String[] arr) throws Exception{
        Stack<Double> opd = new Stack<>();
        Stack<Character> opt=new Stack<>();

        for (int i=0 ; i<arr.length;i++) {
            arr[i]=arr[i].trim();
            if(arr[i].equals("")) continue;

            switch (arr[i].charAt(0)) {
                case '-':
                case '+':
                    while(!opt.isEmpty() && (opt.peek()=='-'|| opt.peek()=='+'|| opt.peek()=='*'|| opt.peek()=='/') )
                        process(opd,opt);
                    opt.add(arr[i].charAt(0));
                    break;
                case '*':
                case '/':
                    if( !opt.isEmpty() && ( opt.peek()=='/' || opt.peek()=='*' )  )
                        process(opd,opt);
                    opt.add(arr[i].charAt(0));
                    break;
                case '(' :
                    opt.add(arr[i].charAt(0));
                    break;
                case ')' :
                    while( opt.peek()!='(')
                        process(opd,opt);
                    opt.pop();
                    break;
                default:
                    opd.add(new Double(arr[i]));
                    break;
            }
        }
        while(!opt.isEmpty())
            process(opd,opt);

        return opd.pop();
    }
    /*************************/
    private static void process(Stack<Double> opd , Stack<Character> opt  ) throws Exception{
        char op=opt.pop();
        Double op1=opd.pop();
        /// to solve - 5 + 1
        Double op2=(opd.isEmpty())? 0 : opd.pop();

        //System.out.println(op1 + " " +op+" " +op2);
        switch(op){
            case '-' : opd.add(op2-op1); break;
            case '*' : opd.add(op2*op1); break;
            case '+' : opd.add(op2+op1); break;
            case '/' :
                if(op1==0) throw new Exception("no divide zero");
                else opd.add(op2/op1); break;
        }
    }

}
