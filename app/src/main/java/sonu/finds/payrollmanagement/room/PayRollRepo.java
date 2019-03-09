package sonu.finds.payrollmanagement.room;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

public class PayRollRepo {

    private static final String TAG = "PayRollRepo";
    private PaYRollDao dao;
    MutableLiveData<PayRollEntity> data = new MutableLiveData<>();
    MutableLiveData<Long> insertedId = new MutableLiveData<>();

    public PayRollRepo(Application application) {
        PayRollDatabase database = PayRollDatabase.getPayRollDatabase(application);
        dao = database.paYRollDao();
    }

    private void asyncFinished(PayRollEntity results) {

        data.setValue(results);
    }
    private  void getLastInsertedId(long id){
        insertedId.setValue(id);
    }

    public void findPayRollData(int id) {
        QueryAsyncTask task = new QueryAsyncTask(dao);
        task.delegate = this;
        task.execute(id);
    }

    private static class QueryAsyncTask extends
            AsyncTask<Integer, Void, PayRollEntity> {

        private PaYRollDao asyncTaskDao;
        private PayRollRepo delegate = null;

        QueryAsyncTask(PaYRollDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected PayRollEntity doInBackground(final Integer... params) {
            return asyncTaskDao.getResult(params[0]);
        }

        @Override
        protected void onPostExecute(PayRollEntity result) {
            delegate.asyncFinished(result);
        }
    }

    public MutableLiveData<PayRollEntity> getData() {
        return data;
    }

    public MutableLiveData<Long> getInsertedId() {
        return insertedId;
    }

    public void Insert(PayRollEntity entity) {
        InsertAsynckTask insertAsynckTask = new  InsertAsynckTask(dao);
        insertAsynckTask.delegate = this;
        insertAsynckTask.execute(entity);
    }

    private static class InsertAsynckTask extends AsyncTask<PayRollEntity, Void, Long> {
        private PaYRollDao dao;
        private PayRollRepo delegate = null;

        public InsertAsynckTask(PaYRollDao dao) {

            this.dao = dao;
        }

        @Override
        protected Long doInBackground(PayRollEntity... payRollEntities) {
            long l = dao.insert(payRollEntities[0]);
            Log.d(TAG, "doInBackground:result of inseted row is  " + l);
            return l;
        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);
            delegate.getLastInsertedId(aVoid);
            //myInterface.getData(aVoid);
            //  Constants.id_value = aVoid;
        }
    }
}
