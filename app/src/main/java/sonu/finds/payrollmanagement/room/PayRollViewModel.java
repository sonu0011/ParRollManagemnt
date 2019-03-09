package sonu.finds.payrollmanagement.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class PayRollViewModel extends AndroidViewModel  {
    private  PayRollRepo payRollRepo;
    private MutableLiveData<PayRollEntity> searchResults;
    private MutableLiveData<Long> lastId;

    public PayRollViewModel(@NonNull Application application) {
        super(application);
        payRollRepo = new PayRollRepo(application);
        searchResults = payRollRepo.getData();
        lastId = payRollRepo.getInsertedId();
    }

    public void insert(PayRollEntity entity){

        payRollRepo.Insert(entity);
    }

    public MutableLiveData<Long> getLastId() {
        return lastId;
    }

    public MutableLiveData<PayRollEntity> getSearchResults() {
        return searchResults;
    }
    public void findPayRollEntity(int id) {

        payRollRepo.findPayRollData(id);
    }
}
