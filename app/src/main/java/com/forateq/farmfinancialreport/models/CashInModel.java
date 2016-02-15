package com.forateq.farmfinancialreport.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Vallejos Family on 2/10/2016.
 */
@Table(name = "CashIn")
public class CashInModel extends Model {

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "date")
    private String date;

    @Column(name = "amount")
    private String amount;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "attachment_file")
    private String attachmentFile;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public static void insertCashIn(String projectName, String date, String amount, String category, String description, String attachmentFile){
        CashInModel cashInModel = new CashInModel();
        cashInModel.setProjectName(projectName);
        cashInModel.setDate(date);
        cashInModel.setAmount(amount);
        cashInModel.setCategory(category);
        cashInModel.setDescription(description);
        cashInModel.setAttachmentFile(attachmentFile);
        cashInModel.save();
    }
}
