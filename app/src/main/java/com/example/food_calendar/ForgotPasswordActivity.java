package com.example.food_calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        final EditText emailField = findViewById(R.id.email_field);
        final Button sendResetButton = findViewById(R.id.send_reset_button);
        final TextView statusMessage = findViewById(R.id.status_message);

        sendResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    statusMessage.setVisibility(View.VISIBLE);
                    statusMessage.setText("Please enter your email address");
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    statusMessage.setVisibility(View.VISIBLE);
                    statusMessage.setText("Please enter a valid email address");
                    return;
                }

                statusMessage.setVisibility(View.VISIBLE);
                statusMessage.setText("A reset link has been sent to your email: " + email);

                emailField.getText().clear();

            }
        });
    }
}
