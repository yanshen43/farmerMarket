/*
 * Created by Zhen Guo on 2016.11.03  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */
package market.mymarket.managers;

import market.mymarket.entityclasses.Photo;
import market.mymarket.entityclasses.Customer;
import market.mymarket.sessionbeans.PhotoFacade;
import market.mymarket.sessionbeans.CustomerFacade;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;
import org.imgscalr.Scalr;
import org.primefaces.model.UploadedFile;

@Named(value = "fileManager")
@SessionScoped
/**
 *
 * @author Balci
 */
public class FileManager implements Serializable {

    // Instance Variables (Properties)
    private UploadedFile file;
    private String message = "";

    /*
    The @EJB annotation implies that the EJB container will perform an injection of the object
    reference of the CustomerFacade object into customerFacade when it is created at runtime.
     */
    @EJB
    private CustomerFacade customerFacade;

    /*
    The @EJB annotation implies that the EJB container will perform an injection of the object
    reference of the PhotoFacade object into photoFacade when it is created at runtime.
     */
    @EJB
    private PhotoFacade photoFacade;

    // Returns the uploaded file
    public UploadedFile getFile() {
        return file;
    }

    // Set the uploaded file
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    // Return the message
    public String getMessage() {
        return message;
    }

    // Set the message
    public void setMessage(String message) {
        this.message = message;
    }

    // Handle the upload of the selected file
    public String upload() {

        // Check if a file is selected
        if (file.getSize() == 0) {
            message = "You need to choose a file first!";
            return "";
        }

        /*
        MIME (Multipurpose Internet Mail Extensions) is a way of identifying files on
        the Internet according to their nature and format. 

        A "Content-type" is simply a header defined in many protocols, such as HTTP, that 
        makes use of MIME types to specify the nature of the file currently being handled.

        Some MIME file types: (See http://www.freeformatter.com/mime-types-list.html)

            JPEG Image      = image/jpeg or image/jpg
            PNG image       = image/png
            Plain text file = text/plain
            HTML file       = text/html
            JSON file       = application/json
         */
        // Obtain the uploaded file's MIME file type
        String mimeFileType = file.getContentType();

        if (mimeFileType.startsWith("image/")) {
            // The uploaded file is an image file
            /*
            The subSequence() method returns the portion of the mimeFileType string from the 6th
            position to the last character. Note that it starts with "image/" which has 6 characters at
            positions 0,1,2,3,4,5. Therefore, we start the subsequence at position 6 to obtain the file extension.
             */
            String fileExtension = mimeFileType.subSequence(6, mimeFileType.length()).toString();

            String fileExtensionInCaps = fileExtension.toUpperCase();

            if (fileExtensionInCaps.endsWith("JPG") || fileExtensionInCaps.endsWith("JPEG") || fileExtensionInCaps.endsWith("PNG")) {
                // File type is acceptable
            } else {
                message = "The uploaded file type is not a JPG, JPEG or PNG!";
                return "";
            }
        } else {
            message = "The uploaded file must be an image file of type JPG, JPEG or PNG!";
            return "";
        }

        storePhotoFile(file);
        message = "";

        // Redirect to show the Profile page
        return "Profile?faces-redirect=true";
    }

    // Cancel file upload
    public String cancel() {
        message = "";
        return "Profile?faces-redirect=true";
    }

    // Store the uploaded photo file and its thumbnail version and create a database record 
    public FacesMessage storePhotoFile(UploadedFile file) {
        try {

            // Delete logged-in customer's uploaded photo file, its thumbnail file, tmp_file, and its database record.
            deletePhoto();

            /*
            InputStream is an abstract class, which is the superclass of all classes representing an input stream of bytes.
            Convert the uploaded file into an input stream of bytes.
             */
            InputStream inputStream = file.getInputstream();

            // Write the uploaded file's input stream of bytes into the file named TEMP_FILE = "tmp_file"
            File tempFile = inputStreamToFile(inputStream, Constants.TEMP_FILE);

            // Close the input stream and release any system resources associated with it
            inputStream.close();

            FacesMessage resultMsg;

            // Obtain the username of the logged-in customer
            String user_name = (String) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("username");

            // Obtain the object reference of the logged-in Customer object
            Customer customer = customerFacade.findByUsername(user_name);

            // Obtain the uploaded file's MIME file type
            String mimeFileType = file.getContentType();

            // If it is an image file, obtain its file extension; otherwise, set png as the file extension anyway.
            String fileExtension = mimeFileType.startsWith("image/") ? mimeFileType.subSequence(6, mimeFileType.length()).toString() : "png";

            /*
            Obtain the list of Photo objects that belong to the Customer whose
            database primary key is customer.getId()
             */
            List<Photo> photoList = photoFacade.findPhotosByCustomerID(customer.getId());

            if (!photoList.isEmpty()) {
                // Remove the photo from the database
                photoFacade.remove(photoList.get(0));
            }

            // Construct a new Photo object with file extension and customer's object reference
            Photo newPhoto = new Photo(fileExtension, customer);

            // Create a record for the new Photo object in the database
            photoFacade.create(newPhoto);

            // Obtain the object reference of the first Photo object of the
            // customer whose primary key is customer.getId()
            Photo photo = photoFacade.findPhotosByCustomerID(customer.getId()).get(0);

            // Reconvert the uploaded file into an input stream of bytes.
            inputStream = file.getInputstream();

            // Write the uploaded file's input stream of bytes under the photo object's
            // filename using the inputStreamToFile method given below
            File uploadedFile = inputStreamToFile(inputStream, photo.getFilename());

            // Create and save the thumbnail version of the uploaded file
            saveThumbnail(uploadedFile, photo);

            // Compose the result message
            resultMsg = new FacesMessage("Success!", "File Successfully Uploaded!");

            // Return the result message
            return resultMsg;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FacesMessage("Upload failure!",
                "There was a problem reading the image file. Please try again with a new photo file.");
    }

    /**
     * @param inputStream of bytes to be written into file with name targetFilename
     * @param targetFilename
     * @return the created file targetFile
     * @throws IOException
     */
    private File inputStreamToFile(InputStream inputStream, String targetFilename) throws IOException {

        /*
        inputStream.available() returns an estimate of the number of bytes that can be read from
        the inputStream without blocking by the next invocation of a method for this input stream.
        A memory buffer of bytes is created with the size of estimated number of bytes.
         */
        byte[] buffer = new byte[inputStream.available()];

        // Read the bytes of data from the inputStream into the created memory buffer. 
        inputStream.read(buffer);

        // Create targetFile with the given targetFilename in the ROOT_DIRECTORY    
        File targetFile = new File(Constants.ROOT_DIRECTORY, targetFilename);

        // A file OutputStream is an output stream for writing data to a file
        OutputStream outStream;

        /*
        FileOutputStream is intended for writing streams of raw bytes such as image data.
        Create a new FileOutputStream for writing to targetFile
         */
        outStream = new FileOutputStream(targetFile);

        // Write the inputStream from the memory buffer into the targetFile
        outStream.write(buffer);

        // Close the output stream and release any system resources associated with it. 
        outStream.close();

        // Return the created file targetFile
        return targetFile;
    }

    /*
    When customer uploads a photo, a thumbnail (small) version of the photo image
    is created in this method by using the Scalr.resize method provided in the
    imgscalr (Java Image Scaling Library) imported as imgscalr-lib-4.2.jar
     */
    private void saveThumbnail(File inputFile, Photo inputPhoto) {
        try {
            // Buffer the photo image from the uploaded inputFile
            BufferedImage uploadedPhoto = ImageIO.read(inputFile);

            // Scale the uploaded photo image to the THUMBNAIL_SIZE using imgscalr 
            BufferedImage thumbnailPhoto = Scalr.resize(uploadedPhoto, Constants.THUMBNAIL_SIZE);

            // Create the thumbnail photo file in the ROOT_DIRECTORY
            File thumbnailPhotoFile = new File(Constants.ROOT_DIRECTORY, inputPhoto.getThumbnailName());

            // Write the thumbnailPhoto into thumbnailPhotoFile with the file extension
            ImageIO.write(thumbnailPhoto, inputPhoto.getExtension(), thumbnailPhotoFile);

        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Delete uploaded photo, thumbnail photo, and tmp_file that belong to
    the logged-in Customer object and remove the photo's database record.
     */
    public void deletePhoto() {

        FacesMessage resultMsg;

        // Obtain the username of the logged-in customer
        String user_name = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("username");

        // Obtain the object reference of the logged-in Customer object
        Customer customer = customerFacade.findByUsername(user_name);

        /*
        Obtain the list of Photo objects that belong to the logged-in
        Customer object whose database primary key is customer.getId()
         */
        List<Photo> photoList = photoFacade.findPhotosByCustomerID(customer.getId());

        if (photoList.isEmpty()) {

            resultMsg = new FacesMessage("Error", "You do not have a photo to delete.");

        } else {
            // Obtain the object reference of the first Photo object in the list
            Photo photo = photoList.get(0);
            try {
                // Delete the uploaded photo file if it exists
                Files.deleteIfExists(Paths.get(photo.getFilePath()));

                // Delete the thumbnail image file if it exists
                Files.deleteIfExists(Paths.get(photo.getThumbnailFilePath()));

                // Delete the temporary file if it exists
                Files.deleteIfExists(Paths.get(Constants.ROOT_DIRECTORY + "tmp_file"));

                // Remove the customer photo's record from the database
                photoFacade.remove(photo);

            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            resultMsg = new FacesMessage("Success", "Photo successfully deleted!");
        }
        FacesContext.getCurrentInstance().addMessage(null, resultMsg);
    }
}
