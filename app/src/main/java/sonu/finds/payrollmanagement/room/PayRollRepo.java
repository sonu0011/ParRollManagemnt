package sonu.finds.payrollmanagement.room;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

public class PayRollRepo  {
    private static final String TAG = "PayRollRepo";
    private PaYRollDao dao;
    MutableLiveData<PayRollEntity> data = new MutableLiveData<>();

    private void asyncFinished(PayRollEntity results) {
        data.setValue(results);
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

    public PayRollRepo(Application application) {
        PayRollDatabase database = PayRollDatabase.getPayRollDatabase(application);
        dao = database.paYRollDao();


    }

    public void Insert(PayRollEntity entity) {
        new InsertAsynckTask(dao).execute(entity);
    }

    private static class InsertAsynckTask extends AsyncTask<PayRollEntity, Void, Long> {
        private PaYRollDao dao;

        public InsertAsynckTask(PaYRollDao dao) {
            this.dao = dao;
        }

        @Override
        protected Long doInBackground(PayRollEntity... payRollEntities) {
           long l  =  dao.insert(payRollEntities[0]);
            Log.d(TAG, "doInBackground:result of inseted row is  "+l);
            return l;
        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);
           //myInterface.getData(aVoid);
          //  Constants.id_value = aVoid;
        }
    }
}
