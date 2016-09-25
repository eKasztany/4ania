package queueapp.eu.wilek.kolejelasu.services.interfaces;

import android.support.annotation.NonNull;

import queueapp.eu.wilek.kolejelasu.model.service.Group;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public interface OnGroupClickListener {

    void onClick(@NonNull Group group);
}
