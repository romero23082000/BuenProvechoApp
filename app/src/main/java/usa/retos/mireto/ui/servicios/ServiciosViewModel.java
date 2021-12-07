package usa.retos.mireto.ui.servicios;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ServiciosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ServiciosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Pantalla de servicios");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
