package com.forateq.farmfinancialreport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.forateq.farmfinancialreport.models.CashInModel;
import com.forateq.farmfinancialreport.models.CashOutModel;
import com.forateq.farmfinancialreport.views.CashInView;
import com.forateq.farmfinancialreport.views.CashOutView;
import com.forateq.farmfinancialreport.views.ProjectView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String APP_NAME = "Farm Financial Report";
    public static final String PROJECT = "Projects";
    private Toolbar toolbar;
    private LinearLayout projectLayout;
    private LinearLayout cashInLayout;
    private LinearLayout cashOutLayout;
    private LinearLayout mainMenuView;
    private static LinearLayout viewContainer;
    private TextView labelTextView;
    private ImageView backImageView;
    private ImageView editImageView;
    private ImageView deleteImageView;
    private Uri currentUri;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private String mCurrentPhotoPath;
    private File currentFile;
    private Button currentAttachmentButton;
    private TextView currentFileTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        projectLayout = (LinearLayout) findViewById(R.id.project_layout);
        projectLayout.setOnClickListener(this);
        cashInLayout = (LinearLayout) findViewById(R.id.cash_in_layout);
        cashInLayout.setOnClickListener(this);
        cashOutLayout = (LinearLayout) findViewById(R.id.cash_out_layout);
        cashOutLayout.setOnClickListener(this);
        mainMenuView = (LinearLayout) findViewById(R.id.main_menu_view);
        viewContainer = (LinearLayout) findViewById(R.id.view_container);
        labelTextView = (TextView) toolbar.findViewById(R.id.label);
        backImageView = (ImageView) toolbar.findViewById(R.id.back);
        editImageView = (ImageView) toolbar.findViewById(R.id.edit);
        deleteImageView = (ImageView) toolbar.findViewById(R.id.delete);
        backImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.project_layout:{
                ProjectView projectView = new ProjectView(this);
                hideMainMenu(projectView);
                break;
            }
            case R.id.cash_in_layout:{
                final CashInView cashInView = new CashInView(this);
                currentAttachmentButton = cashInView.getAttachmentButton();
                currentFileTextView = cashInView.getImageURITextView();
                currentAttachmentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("Clicked", "Clicked");
                        dispatchTakePictureIntent();
                    }
                });
                final MaterialDialog.Builder createNoteDialog = new MaterialDialog.Builder(this)
                        .title("Cash-In")
                        .titleColorRes(R.color.colorPrimaryText)
                        .backgroundColorRes(R.color.colorPrimaryDark)
                        .widgetColorRes(R.color.colorPrimaryText)
                        .customView(cashInView, true)
                        .positiveText("Ok")
                        .positiveColorRes(R.color.colorPrimaryText)
                        .negativeText("Cancel")
                        .negativeColorRes(R.color.colorPrimaryText)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                                CashInModel.insertCashIn(cashInView.getProjectSpinner().getSelectedItem().toString(), cashInView.getDateEditText().getText().toString(), cashInView.getAmountEditText().getText().toString(), cashInView.getTaskSpinner().getSelectedItem().toString(), cashInView.getDescriptionEditText().getText().toString(), cashInView.getImageURITextView().getText().toString());
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                super.onNegative(dialog);
                            }
                        });
                final MaterialDialog addNoteDialog = createNoteDialog.build();
                addNoteDialog.show();
                break;
            }
            case  R.id.cash_out_layout:{
                final CashOutView cashOutView = new CashOutView(this);
                currentFileTextView = cashOutView.getAttachmentTextView();
                cashOutView.getAttachmentButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dispatchTakePictureIntent();
                    }
                });
                final MaterialDialog.Builder createNoteDialog = new MaterialDialog.Builder(this)
                        .title("Cash-Out")
                        .titleColorRes(R.color.colorPrimaryText)
                        .backgroundColorRes(R.color.colorPrimaryDark)
                        .widgetColorRes(R.color.colorPrimaryText)
                        .customView(cashOutView, true)
                        .positiveText("Ok")
                        .positiveColorRes(R.color.colorPrimaryText)
                        .negativeText("Cancel")
                        .negativeColorRes(R.color.colorPrimaryText)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                                CashOutModel.insertCashOut(cashOutView.getProjectSpinner().getSelectedItem().toString(), cashOutView.getDateEditText().getText().toString(), cashOutView.getAmountEditText().getText().toString(), cashOutView.getTaskSpinner().getSelectedItem().toString(), cashOutView.getDescriptionEditText().getText().toString(), cashOutView.getAttachmentTextView().getText().toString());
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                super.onNegative(dialog);
                            }
                        });
                final MaterialDialog addNoteDialog = createNoteDialog.build();
                addNoteDialog.show();
                break;
            }
            case R.id.back:{
                if(FarmApplication.viewDeque.isEmpty()){
                    showMainMenu();
                }
                else{
                    backView();
                }
                break;
            }

        }
    }

    public void hideMainMenu(View view){
        mainMenuView.setVisibility(View.GONE);
        labelTextView.setText(PROJECT);
        backImageView.setVisibility(View.VISIBLE);
        editImageView.setVisibility(View.VISIBLE);
        deleteImageView.setVisibility(View.VISIBLE);
        viewContainer.removeAllViews();
        viewContainer.addView(view);
        viewContainer.setVisibility(View.VISIBLE);
    }

    public void backView(){
        viewContainer.removeAllViews();
        viewContainer.addView(FarmApplication.viewDeque.removeLast());
        viewContainer.setVisibility(View.VISIBLE);
    }

    public static void replaceView(View view){
        viewContainer.removeAllViews();
        viewContainer.addView(view);
        viewContainer.setVisibility(View.VISIBLE);
    }

    public void showMainMenu(){
        FarmApplication.viewDeque.clear();
        mainMenuView.setVisibility(View.VISIBLE);
        backImageView.setVisibility(View.GONE);
        editImageView.setVisibility(View.GONE);
        deleteImageView.setVisibility(View.GONE);
        viewContainer.setVisibility(View.GONE);
        labelTextView.setText(APP_NAME);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(FarmApplication.getInstance().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                currentUri = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        currentUri);
                Log.e("Current Path", currentUri.getPath());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
            else{
                Log.e("NOT NULL", "NOT NULL");
            }
        }
        else{
            Log.e("NULL", "NULL");
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Farm/Pictures");
        if(!storageDir.exists()){
            storageDir.mkdirs();
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        currentFile = image;
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == this.RESULT_OK) {
            try {
                Log.e("Path",mCurrentPhotoPath);
                currentFileTextView.setText(mCurrentPhotoPath);
                Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
