package org.example;

/*
    Задача
На входе функция получает параметр n - натуральное число. Необходимо сгенерировать n-массивов, заполнить их случайными числами,
каждый массив имеет случайный размер. Размеры массивов не должны совпадать. Далее необходимо отсортировать массивы.
Массивы с четным порядковым номером отсортировать по возрастанию, с нечетным порядковым номером - по убыванию.
На выходе функция должна вернуть массив с отсортированными массивами.
* */
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
public class Main {
    static int separation(boolean tmp, int[] array, int begin, int last){
        int counter = begin;
        for (int i = begin; i < last; i++) {
            if (tmp ? array[i] < array[last] : array[i] > array[last]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        int temp = array[last];
        array[last] = array[counter];
        array[counter] = temp;
        return counter;
    }
    public static void quickSort(boolean tmp, int[] array, int begin, int end) {
        if (end <= begin) return;
        int stem = separation(tmp, array, begin, end);
        quickSort(tmp, array, begin, stem-1);
        quickSort(tmp, array, stem+1, end);
    }
    public static void show(List<int[]> listArr){
        AtomicInteger count = new AtomicInteger();
        listArr.forEach(item -> {
            System.out.println("index: " + count.getAndIncrement() +  ", count: " + item.length);
            for (Integer i: item) {
                System.out.print(i + ", ");
            }
            System.out.println();
        });
    }
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("Введите количество массивов: "); //ручной ввод с клавиатуры
        //int n = scanner.nextInt();
        int maxSizeArray = 30, maxElement = 20, n = 7;
        Random random = new Random();
        List<int []> listArray = new ArrayList<>();
        ist<Integer> randomElement = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] tempArray;
            int m = 1 + random.nextInt(maxSizeArray);
            if (maxSizeArray >= n) {
                while (randomElement.contains(m))
                    m = 1 + random.nextInt(maxSizeArray);
                randomElement.add(m);
            }
            tempArray = new int[m];
            for (int j = 0; j < m; j++)
                tempArray[j] = random.nextInt(maxElement);
            listArray.add(tempArray);
        }
        System.out.println("Массив до сортировки:");
        show(listArray);
        AtomicInteger counter = new AtomicInteger();
        listArray.forEach(item -> {
            // Массивы с четным порядковым номером отсортировать по возрастанию, с нечетным порядковым номером - по убыванию.
            quickSort(counter.getAndIncrement() % 2 == 0 ,item, 0, item.length - 1);
            System.out.println();
        });
        System.out.println("\nМассив после быстрой сортировки:");
        show(listArray);
    }
}