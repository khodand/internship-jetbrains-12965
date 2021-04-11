import java.util.concurrent.*;
import java.util.regex.Pattern;

public class SecondTask {
    public boolean matches(String text, String regex) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Callable<Boolean> callable = () -> Pattern.compile(regex).matcher(text).matches();
        Future<?> future = es.submit(callable);
        Boolean answer = false;
        try {
            answer = (Boolean) future.get(1, TimeUnit.SECONDS);
        } catch(InterruptedException | ExecutionException | TimeoutException e) {
            future.cancel(true);
        }
        es.shutdown();
        return answer;
    }

    public static void main(String[] args) {
        String text = "java";
        String regex = "java";

        SecondTask a = new SecondTask();
        System.out.println(a.matches(text, regex));
    }

}
