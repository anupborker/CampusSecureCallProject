package com.avb.collageproject;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QR_Scanner_Activity extends AppCompatActivity {

    CodeScanner UserIdScanner;
    CodeScannerView UserIDScannerView;
    Button LOGIN;
    FirebaseDatabase entrydatabse;
    DatabaseReference reference;

    public String QRUserID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        UserIDScannerView = findViewById(R.id.scanner_view);
        LOGIN = findViewById(R.id.login_button);




        int PERMISSON_ALL = 1;

        String[] PERMISSON = {
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_IMAGES,
        };

        if (!hasPermissions(this, PERMISSON)) {
            ActivityCompat.requestPermissions(this, PERMISSON, PERMISSON_ALL);
        } else {

            runcodeScanner();
        }

        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(QR_Scanner_Activity.this,MainActivity.class);
                startActivity(login);
            }
        });
    }
    private void runcodeScanner() {
        UserIdScanner = new CodeScanner(this, UserIDScannerView);
        UserIdScanner.setAutoFocusEnabled(true);
        UserIdScanner.setFormats(CodeScanner.ALL_FORMATS);
        UserIdScanner.setScanMode(ScanMode.CONTINUOUS);
        UserIdScanner.startPreview();
        UserIdScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        QRUserID = result.getText();
                        AddStudentInDataDB();
                    }
                });
            }
        });
    }

    private void AddStudentInDataDB() {
        entrydatabse = FirebaseDatabase.getInstance();
        reference = entrydatabse.getReference("CampusEntry");
        System.out.println(reference);
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat timeformate = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String entryDate = formatter.format(date);
        String entryRefId = QRUserID + "" +entryDate;



        reference.child(entryRefId).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot);
                Log.d("QRScannerActivity", "snapshot.exists(): " + snapshot.exists());
                Log.d("QRScannerActivity", "snapshot.hasChild(\"timeofentry\"): " + snapshot.hasChild("timeofentry"));


                if (snapshot.exists() && snapshot.hasChild("timeofentry")) {
                    Log.d("TAG", "Snapshot exists and has timeofentry child");
                    String exitTime = timeformate.format(date);
                    reference.child(entryRefId).child("timeofexit").setValue(exitTime);
                    Toast.makeText(QR_Scanner_Activity.this, "Exit time updated", Toast.LENGTH_SHORT).show();
                }else {

                    String timeofentry = timeformate.format(date);
                    DatabaseReference newEntry = reference.child(entryRefId);
                    newEntry.child("entryRefId").setValue(entryRefId);
                    newEntry.child("userID").setValue(QRUserID);
                    newEntry.child("dateofentry").setValue(entryDate);
                    newEntry.child("timeofentry").setValue(timeofentry);
                    Toast.makeText(QR_Scanner_Activity.this, "Entry Time Updated", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Snapshot Added");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QR_Scanner_Activity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

    }



    // Helper function to check if camera permission is granted
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Override onRequestPermissionsResult to handle camera permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                runcodeScanner();
            } else {
                Toast.makeText(this, "Camera permission is required to scan QR codes", Toast.LENGTH_SHORT).show();
            }
        }
    }
}