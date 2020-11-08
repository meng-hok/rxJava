package src;


import rx.*;
import rx.schedulers.Schedulers;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Processor {
    static Observable<String> lines(BufferedReader reader)  {
        return Observable.<String>create(subscriber -> {
            String line;
            try{
                while ((line = reader.readLine()) != null){

                    if("end".equals(line))
                        subscriber.onError(new Exception("hahahaha"));
                    else
                        subscriber.onNext(line);

                    if(subscriber.isUnsubscribed())
                        break;
                }

            }catch (Exception ex){

            }finally {
                subscriber.onCompleted();
            }

        }).subscribeOn(Schedulers.io());
    }

    static Observable<String> linesFromInput() {
        return lines(new BufferedReader(new InputStreamReader(System.in)));
    }

}
