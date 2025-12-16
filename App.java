
public class App {
    public static void main(String[] args) throws Exception 
    {
        int a = 0;
        for(int i = 1; i < 10; i++){
            if (i == 1){ a = 2000; }
            if (i == 2){ a = 5000; }
            if (i == 3){ a = 10_000; }
            if (i == 4){ a = 22_000; }
            if (i == 5){ a = 53_000; }
            if (i == 6){ a = 100_000; }
            if (i == 7){ a = 220_000; }
            if (i == 8){ a = 330_000; }
            if (i == 9){ a = 510_000; }

            System.out.println("массив длиной: " + a);
            int[] mas1 = new int[a];
            int[] mas2 = new int[a];
            int[] mas3 = new int[a];
            massiv(mas1);
            massiv(mas2);
            massiv(mas3);
            
            double time = System.currentTimeMillis();
            Shella(mas1);
            System.out.println("время выполнения сортировки Шелла: " + (System.currentTimeMillis() - time + " миллисекунд"));

            time = System.currentTimeMillis();
            Bubble(mas2);
            System.out.println("время выполнения сортировки пузырьки: " + (System.currentTimeMillis() - time + " миллисекунд"));

            time = System.currentTimeMillis();
            Quick(mas3, 0, mas3.length - 1);
            System.out.println("время выполнения для быстрой сортировки со среднем элементом в качестве опорного: " + (System.currentTimeMillis() - time + " миллисекунд"));
            System.out.println();
        }

        
    }

    public static void massiv(int[] mas)
    {
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int) (Math.random() * 100_000);
        }
    }

    public static int[] Shella(int[] mas) {
        int n = mas.length;
        for (int gap = n/2; gap > 0; gap /= 2){
            for (int i = gap; i < n; i++){
                int temp = mas[i];
                int j;
                for (j = i; j >= gap && mas[j - gap] > temp; j -= gap){
                    mas[j] = mas [j - gap];
                }
                mas[j] = temp;
            }
        }
        return mas;
    }

    public static int[] Bubble(int[] mas) {
        int n = mas.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (mas[j] > mas[j + 1]) {
                    int temp = mas[j];
                    mas[j] = mas[j + 1];
                    mas[j + 1] = temp;
                }
            }
        }
        return mas;
    }

    public static void Quick(int[] mas, int low, int high) {
        if (low < high) {
            int pivot = partition(mas, low, high);
            Quick(mas, low, pivot - 1);
            Quick(mas, pivot + 1, high);
        }
    }

    public static int partition(int[] mas, int low, int high) {
        int pivot = mas[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (mas[j] <= pivot) {
                i++;
                int temp = mas[i];
                mas[i] = mas[j];
                mas[j] = temp;
            }
        }

        int temp = mas[i + 1];
        mas[i + 1] = mas[high];
        mas[high] = temp;

        return i + 1;
    }
}
