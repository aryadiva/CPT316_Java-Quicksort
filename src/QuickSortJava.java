import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickSortJava {
    public static void main(String[] args) throws IOException {
        QuickSortJava sorter = new QuickSortJava(); // Creating an instance of method to call

        File file = new File("src/Data.txt"); // Navigate the text file to be read
        File txt = new File("src/Quick-Output.txt"); // creating a new file to store the sorted words
        FileWriter writer = new FileWriter(txt); // To write to the newly created sorted file

        String[] str_list = new String[0]; // initializing str_list to store the string to array
        try {
            Scanner myReader = new Scanner(file); // to scan the content of the file to be read
            List<String> list = new ArrayList<String>(); // creating an instance of list
            while (myReader.hasNext()) {
                // while myReader detect any next line it will add that line to list
                list.add(myReader.next());
            }

            int length = list.size(); // initialize length size
            str_list = new String[length]; // creating a new array to store the sorted words
            str_list = list.toArray(str_list); // converting list to array

            myReader.close(); // closing myReader
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            long startTime = System.currentTimeMillis();

            // calling the quicksort method
            sorter.quickSort(str_list, 0, str_list.length-1);

            long endTime   = System.currentTimeMillis();
            long totalTime = (endTime - startTime);

            //-------To write the sorted result to external txt file
            for(int i = 0; i < str_list.length; i++){
                writer.write(str_list[i]+'\n'); // writing each element(i) in array to a new file
            }
            writer.close();
            System.out.println("Run time(ms): "+totalTime);
        }
    }

    public void quickSort(String[] array, int lowerIndex, int higherIndex) throws IOException {

        // if the array is null or has length of 0, return this method without doing anything
        if (array == null || array.length == 0) {

            return;
        }

        int i = lowerIndex; // set i as the lower index
        int j = higherIndex; // set j as the high index

        /*----------------------------------------------------------
        String pivot = array[higherIndex];  // worst case
        String pivot = array[lowerIndex];   // worst case

        // average case for quicksort is referring twhen the array elements in the  list is in a random order
        // so average case has already been implemented and has the same time complexity as best case
         ---------------------------------------------------------*/

        // the pivot is based on the middle element of the whole array
        String pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2]; // Best case scenario

        // while low index is smaller or equal than high index
        while (i <= j) {
            // while element i in array is lexicographically less than the pivot
            // i will keep incrementing until the condition is false

            while (array[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            // while element j in array is lexicographically greater than the pivot
            // j will keep decrementing until the condition is false
            while (array[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            // if i is lower or equal than j
            if (i <= j) {
                // calling swap method
                swap(array, i, j);
                i++;
                j--;
            }
        }
        //call quickSort recursively based on given condition

        if (lowerIndex < j) {
            // if the end lower index is smaller than j it will recursively call quicksort method again
            // but this time the parameter to be passed instead of higher index it will be j
            quickSort(array, lowerIndex, j);
        }

        if (i < higherIndex) {
            // if i is smaller than higher index it will recursively call quicksort method again
            // but this time the parameter to be passed instead of lower index it will be i
            quickSort(array, i, higherIndex);
        }
    }

    void swap(String[] array, int i, int j) {
        // the swap method is for swapping places between i & j in the array
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
