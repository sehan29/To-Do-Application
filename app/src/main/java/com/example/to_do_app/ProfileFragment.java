package com.example.to_do_app;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 2;

    private TextView textView2; // For username
    private TextView textView11; // For birthdate
    private TextView textView13; // For phone number
    private ImageView profileImageView; // Profile picture

    private Uri selectedImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textView2 = view.findViewById(R.id.textView2); // For username
        textView11 = view.findViewById(R.id.textView11); // For birthdate
        textView13 = view.findViewById(R.id.textView13); // For phone number
        profileImageView = view.findViewById(R.id.imageView2); // Profile picture

        // Find the Log Out button and set a click listener
        Button logoutButton = view.findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the log out confirmation dialog
                showLogoutDialog();
            }
        });

        Button editProfileButton = view.findViewById(R.id.button2);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the edit profile dialog
                showEditProfileDialog();
            }
        });

        return view;
    }

    private void showLogoutDialog() {
        // Create an AlertDialog to confirm log out
        new AlertDialog.Builder(getContext())
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform the log out action
                        performLogout();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void performLogout() {
        // Add your log out logic here

        // Create an Intent to start the LoginActivity
        Intent intent2 = new Intent(getContext(), Login_Page.class);
        // Add flags to clear the back stack and start a new task
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Start the LoginActivity
        startActivity(intent2);
        // Finish the current activity (ProfileActivity or any other activity that contains the ProfileFragment)
        getActivity().finish();
    }

    private String currentUsername = "Sehan Hansaja Gamage";
    private String currentBirthdate = "29 - November - 2001";
    private String currentPhoneNumber = "+94 70 287 6795";

    private void showEditProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Edit Profile");

        // Inflate the layout for the dialog
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_profile, null);
        EditText usernameEditText = view.findViewById(R.id.editTextUsername);
        EditText birthdateEditText = view.findViewById(R.id.editTextBirthdate);
        EditText contactEditText = view.findViewById(R.id.editTextContact);
        ImageView editProfileImageView = view.findViewById(R.id.editProfileImageView);
        Button changeProfilePictureButton = view.findViewById(R.id.changeProfilePictureButton);

        // Populate EditText fields with current profile details
        usernameEditText.setText(currentUsername);
        birthdateEditText.setText(currentBirthdate);
        contactEditText.setText(currentPhoneNumber);

        if (selectedImageUri != null) {
            editProfileImageView.setImageURI(selectedImageUri);
        }

        changeProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                } else {
                    openImageChooser();
                }
            }
        });

        builder.setView(view);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Retrieve user input from EditText fields
                String newUsername = usernameEditText.getText().toString();
                String newBirthdate = birthdateEditText.getText().toString();
                String newPhoneNumber = contactEditText.getText().toString();

                // Update the profile details
                updateProfile(newUsername, newBirthdate, newPhoneNumber);
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            showEditProfileDialog();
        }
    }

    private void updateProfile(String newUsername, String newBirthdate, String newPhoneNumber) {
        // Update the profile details with the new values

        // Update the current profile details
        currentUsername = newUsername;
        currentBirthdate = newBirthdate;
        currentPhoneNumber = newPhoneNumber;

        // Update the TextViews with the new values
        textView2.setText(currentUsername);
        textView11.setText(currentBirthdate);
        textView13.setText(currentPhoneNumber);

        if (selectedImageUri != null) {
            profileImageView.setImageURI(selectedImageUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImageChooser();
            } else {
                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
