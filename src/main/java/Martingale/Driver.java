package Martingale;

import java.util.Random;

public class Driver {
	private static int startingAmount;
	private static int baseBet;
	private static int curBet;
	private static int curBalance;

	private static int numRepeats;

	public static void main(String[] args) {
		init();
		Random random = new Random();

		long totalIterations = 0;
		int numIterations = 0;

		long highestBalance = 0;
		long highestBalanceIters = 0;
		long maxIters = Integer.MIN_VALUE;
		long minIters = Integer.MAX_VALUE;
		for (int i = 0; i < numRepeats; i++) {
			while (curBalance > 0 && curBet <= curBalance) {
				int randNum = random.nextInt(2);

				if (curBalance > highestBalance) {
					highestBalance = curBalance;
					highestBalanceIters = numIterations;
				}

				if (randNum == 0) {
					curBalance += curBet;
					curBet = baseBet;

				} else {
					curBalance -= curBet;
					curBet *= 2;
				}

				numIterations++;
			}

			if (numIterations > maxIters) {
				maxIters = numIterations;

			} else if (numIterations < minIters) {
				minIters = numIterations;
			}

			totalIterations += numIterations;
			numIterations = 0;
			init();
		}

		System.out.println("////////////////////////////////////////////////////////////");
		System.out.println("Avg iters: " + (totalIterations / numRepeats) + ", max: " + maxIters + ", min: " + minIters);
		System.out.println("\nStats:\nMax balance: " + highestBalance + " (" + highestBalanceIters + " iters)\nStart amount: " + startingAmount + "\nBase bet: " + baseBet + "\nNum repeats: " + numRepeats);
		System.out.println("////////////////////////////////////////////////////////////");
	}

	private static void init() {
		startingAmount = 4264; // Balance to start with
		baseBet = 100; // The default bet amount (winning will give you this much every time)
		numRepeats = 100000; // Number of times to run the simulation until you can't bet anymore (higher = more accurate avg iters)

		curBet = baseBet;
		curBalance = startingAmount;
	}
}
