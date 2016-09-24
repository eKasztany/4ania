package queueapp.eu.wilek.kolejelasu.database;

import java.util.List;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public interface FetcherListener<T> {

    void onDataLoaded(List<T> items);

    void onError();
}
