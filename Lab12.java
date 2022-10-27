
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Lab12 {
	public static void main(String [] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("please input the path of the code file");
		String path=sc.nextLine();
		System.out.println("please input the completion level ");
		int level=sc.nextInt();
		String get=read(path);
		if(level==1) {
			lev1(get);
		}
		else if(level==2) {
			lev2(get);
		}
		else {
			search3(get,level);
		}
	}
	public static String read(String path) throws IOException {
	     File file=new File(path);
	     FileReader reader=new FileReader(file);
	     StringBuilder sb=new StringBuilder();
	     BufferedReader br=new BufferedReader(reader);
	     String s;
	     while ((s=br.readLine())!=null) {
	         sb.append(s);
	     }
	     return (sb.toString());
	 }
	public static int search1(String get, String target) {
	     int start = 0;
	     int count = 0;
	     int length = target.length();
	     while (true) {
	    	 int k=get.indexOf(target,start);
	         if(k!=-1&&!get.substring(k-1,k).matches("[a-zA-Z]")
	                 &&!get.substring(k+length,k+length+1).matches("[a-zA-Z]")){
	             count++;
	             start=k;
	             start+=length;
	         } else {
	            break;
	         }
	     }
	     return count;
	 }
	public static int[] search2(String get,String target1,String target2,int start) {
		int t []=new int [2];
		int length=target1.length();
	    int k=get.indexOf(target1,start);
	    int l=get.indexOf(target1,k+length+1);
	    String p;
	    if(l==-1) {
	    	p=get.substring(k);
	    }
	    else {
	    	p=get.substring(k,l);
	    }
	    t[1]=search1(p,target2);
        t[0]=l;
        return t;
	}
	public static void search3(String get, int level) {
		 lev2(get);
	     Stack<String> s1=new Stack();
	     Pattern pattern=Pattern.compile("else *if|else|if");
	     Matcher matcher=pattern.matcher(get);
	     int ifesif=0;
	     int ifes=0;
	     boolean noEsif=true;
	     while (matcher.find()) {
	         String subs=get.substring(matcher.start(), matcher.end());
	         if(!subs.equals("else")) {           
	             s1.push(subs);
	         } 
	         else{           
	             while(!s1.peek().equals("if")){
	                 s1.pop();
	                 if (noEsif) {
	                     ifesif++;
	                     noEsif=false;
	                 }
	             }
	             if (noEsif==true) {
	                 ifes++;
	             }
	             s1.pop();
	             noEsif=true;
	         }
	     }
	     if (level==3) {
	         System.out.println("if-else num: "+ifes);
	     } else {
	    	 System.out.println("if-else num: "+ifes);
	         System.out.println("if-elseif-else num: "+ifesif);
	     }
	 }
	public static void lev1(String get) {
	     int count=0;
	     String keyword[] = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for",
                 "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef",
                 "unsigned", "union", "void", "volatile", "while"};

	     for (int i=0;i<keyword.length;i++) {
	         count=count+search1(get,keyword[i]);
	     }
	     System.out.println("total num: "+count);
	 }
	 
	 public static void lev2(String get) {
	     lev1(get);
	     int h []=new int [2];
	     int t=search1(get, "switch");
	     int p=0;
	     System.out.println("switch num: "+t);
	     System.out.print("case num: ");
	     for(int i=0;i<t;i++) {
	    	 h=search2(get,"switch","case",p);
	         if(i!=t-1) {
	        	 System.out.print(h[1]+" ");
	        	 p=h[0];
	        	 }
	         else {
	        	 System.out.println(h[1]);
	     }
	     }
	 }
}
