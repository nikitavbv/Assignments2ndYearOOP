package com.nikitavbv.univ.jlab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static final int BASE = 10;
  private static final int TOTAL_DIGITS = 6;

  private final Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    new Main().run();
  }

  public void run() {
    while (true) {
      int number = requestNumber();

      Deque<Integer> digits = numberToDigits(number);
      printNumberDigitByDigit(digits);

      System.out.println("reversing number...");

      Deque<Integer> reversed = reverse(digits);
      System.out.println("reversed number: " + digitsToNumber(reversed));
      printNumberDigitByDigit(reversed);

      System.out.println("is symmetric: " + reversed.equals(digits));

      System.out.println("one more? y/n");
      if (!input.nextLine().replace("\n", "").equalsIgnoreCase("y")) {
        break;
      }
    }
  }

  private int requestNumber() {
    while (true) {
      System.out.printf("Enter %d-digit number: ", TOTAL_DIGITS);
      int number = Integer.parseInt(input.nextLine());
      int digits = (int) Math.log10(number) + 1;

      if (digits != TOTAL_DIGITS) {
        System.out.println("Invalid number of digits: " + digits);
      } else {
        return number;
      }
    }
  }

  private Deque<Integer> numberToDigits(int number) {
    Deque<Integer> digits = new LinkedList<>();

    while (number != 0) {
      digits.push(number % BASE);
      number /= BASE;
    }

    return digits;
  }

  private int digitsToNumber(Deque<Integer> digits) {
    Deque<Integer> digitsCopy = new LinkedList<>(digits);

    int result = 0;

    while (!digitsCopy.isEmpty()) {
      result *= BASE;
      result += digitsCopy.pop();
    }

    return result;
  }

  private <T extends Number> Deque<T> reverse(Deque<T> number) {
    Deque<T> digits = new LinkedList<>(number);
    Deque<T> reversedDigits = new LinkedList<>();

    List<T> before = (List) number;
    Collections.reverse(before);

//    while (!digits.isEmpty()) {
//      reversedDigits.push(digits.pop());
//    }

    return (Deque) before;
  }

  private <T extends Number> void printNumberDigitByDigit(Deque<T> number) {
    Deque<T> digits = new LinkedList<>(number);

    System.out.println("number digits (stack):");

    while (!digits.isEmpty()) {
      System.out.println(digits.pop());
    }
  }
}
