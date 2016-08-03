package week1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * week1.TrieMatchingTest class - client program to test the week1.TrieMatching data structure
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 2.0 August 2nd, 2016
 */
public class TrieMatchingTest implements Runnable {

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	String[] patterns = new String[n];
			for (int i = 0; i < n; i++) {
				patterns[i] = in.readLine ();
			}
			TrieMatching trieMatching = new TrieMatching(text, patterns);
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatchingTest ()).start ();
	}
}



