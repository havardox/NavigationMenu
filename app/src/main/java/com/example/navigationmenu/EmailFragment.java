package com.example.navigationmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * {@link Fragment} that sends an email.
 */
public class EmailFragment extends Fragment {

    EditText editEmail, editSubject, editMessage;
    Button btn_Send;

    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        // Find the EditText field for the email recipient
        editEmail = (EditText) view.findViewById(R.id.editEmail);
        // Find the EditText field for the email subject
        editSubject = (EditText) view.findViewById(R.id.editSubject);
        // Find the EditText field for the email message
        editMessage = (EditText) view.findViewById(R.id.editMessage);
        // Find the button that sends the email
        btn_Send = (Button) view.findViewById(R.id.btn_send);
        // Set a click listener to send an email when the button is clicked
        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract the recipient string
                String to = editEmail.getText().toString();
                // Extract the subject string
                String subject = editSubject.getText().toString();
                // Extract the message string
                String message = editMessage.getText().toString();

                // Create a new intent
                Intent intent = new Intent(Intent.ACTION_SEND);
                // Add the array of email address strings to the intent
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                // Add the email subject to the intent
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                // Add the email message to the intent
                intent.putExtra(Intent.EXTRA_TEXT, message);

                // Set a rcf822 MIME type (only email apps should handle this)
                intent.setType("message/rfc822");

                // Send the intent
                startActivity(Intent.createChooser(intent, "Select Email app"));
            }
        });
        return view;
    }
}