package it.jac.corsojava.maxnum;

public class maxNum {
	static int max;
	
	public static void main(String[] args) {
		
		for (int i=0; i<(args.length); i++) {
			if (Integer.parseInt(args[i])>max) {
				max = Integer.parseInt(args[i]);
			}
		}
		
		System.out.println("Il numero maggiore è :" + max);
	}
}
