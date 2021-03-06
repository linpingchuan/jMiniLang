package priv.bajdcc.LALR1.grammar.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import priv.bajdcc.LALR1.grammar.Grammar;
import priv.bajdcc.LALR1.syntax.handler.SyntaxException;
import priv.bajdcc.util.lexer.error.RegexException;

@SuppressWarnings("unused")
public class TestGrammar {

	public static void main(String[] args) {
		try {
			//String expr = "void main(int argc, char** argv) {return 0;}";
			//BufferedReader br = new BufferedReader(new FileReader("E:/http.c"));
			BufferedReader br = new BufferedReader(new FileReader("E:/a.c"));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line).append(System.lineSeparator());
			}
			br.close();
			Grammar grammar = new Grammar(sb.toString());
			//System.out.println(grammar.getNGAString());
			System.out.println(grammar.getNPAString());
			System.out.println(grammar.getInst());
			System.out.println(grammar.getTrackerError());
			System.out.println(grammar.getTokenList());
			System.out.println(grammar.getObject());
			//FileWriter fw = new FileWriter("E:/testgrammar.txt");
			//fw.append(grammar.toString());
			//fw.close();
		} catch (RegexException e) {
			System.err.println(e.getPosition() + "," + e.getMessage());
			e.printStackTrace();
		} catch (SyntaxException e) {
			System.err.println(e.getPosition() + "," + e.getMessage() + " "
					+ e.getInfo());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
