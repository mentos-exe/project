package xml.enc3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project1ver.R;

public class Enc3Fragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Enc3ViewModel enc3ViewModel =
                new ViewModelProvider(this).get(Enc3ViewModel.class);

        View root = inflater.inflate(R.layout.fragment_enc3, container, false);
        final TextView text_enc3 = root.findViewById(R.id.text_enc3);
        enc3ViewModel.getText().observe(getViewLifecycleOwner(), text_enc3::setText);
        final EditText text = root.findViewById(R.id.text);
        final Button encoder = root.findViewById(R.id.encoder);
        enc3ViewModel.getText2().observe(getViewLifecycleOwner(), encoder::setText);
        final EditText resultTextView = root.findViewById(R.id.resultTextView);

        encoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String request = String.valueOf(text.getText());
                StringBuilder encryptedText = new StringBuilder();
                for (int i = 0; i < request.length(); i++) {
                    char c = request.charAt(i);
                    encryptedText.append(Integer.toBinaryString(c)).append(' ');
                }


                resultTextView.setText(encryptedText);
            }
        });

        final TextView resultTextView2 = root.findViewById(R.id.resultTextView2);
        final Button decoder = root.findViewById(R.id.decoder);
        enc3ViewModel.getText3().observe(getViewLifecycleOwner(), decoder::setText);

        decoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String request = String.valueOf(resultTextView.getText());
                String[] binaryArray = request.split(" "); // разделение двоичных чисел

                StringBuilder result = new StringBuilder();
                for (String binary : binaryArray) {
                    int decimal = Integer.parseInt(binary, 2); // перевод двоичного числа в десятичное
                    char character = (char) decimal; // получение символа из десятичного значения
                    result.append(character);
                }



                resultTextView2.setText(result);



            }
        });
        return root;
    }


}