import java.io.BufferedReader;
import java.io.InputStreamReader;

interface Accumulator {
    int fold(int[] arr);
}

class PrimeCount implements Accumulator {
    @Override
    public int fold(int[] inputArray) {
        Accumulator primeAccumulator = (arr) -> {
            int count = 0;
            for (int num : arr) {
                if (Main.isPrime(num)) {
                    count++;
                }
            }
            return count;
        };
        
        return primeAccumulator.fold(inputArray);
    }
}

class NonPrimeCount implements Accumulator {
    @Override
    public int fold(int[] inputArray) {
        Accumulator nonPrimeAccumulator = (arr) -> {
            int count = 0;
            for (int num : arr) {
                if (!Main.isPrime(num)) {
                    count++;
                }
            }
            return count;
        };
        
        return nonPrimeAccumulator.fold(inputArray);
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Введите размер массива: ");
            String sizeInput = br.readLine();
            int[] sizeResult = new int[1];
            while (!tryParseInt(sizeInput, sizeResult) || sizeResult[0] <= 0) {
                System.out.print("Введите корректный положительный размер массива: ");
                sizeInput = br.readLine();
            }
            int size = sizeResult[0];
            
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                System.out.print("Введите элемент " + (i + 1) + ": ");
                String elementInput = br.readLine();
                int[] elementResult = new int[1];
                while (!tryParseInt(elementInput, elementResult)) {
                    System.out.print("Введите корректное целое число: ");
                    elementInput = br.readLine();
                }
                arr[i] = elementResult[0];
            }
            
            PrimeCount primeCount = new PrimeCount();
            NonPrimeCount nonPrimeCount = new NonPrimeCount();
            
            int primeCounter = primeCount.fold(arr);
            int nonPrimeCounter = nonPrimeCount.fold(arr);
            
            System.out.println("Количество простых чисел: " + primeCounter);
            System.out.println("Количество непростых чисел: " + nonPrimeCounter);
            
        } catch (Exception e) {
            System.out.println("Ошибка ввода");
        }
    }
    
    public static boolean tryParseInt(String str, int[] result) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            result[0] = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}