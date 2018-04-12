/*
 * Created by Zhen Guo on 2016.11.03  * 
 * Copyright © 2016 Zhen Guo. All rights reserved. * 
 */

 /*
================================
PrimeFaces Galleria UI Component
================================
Copied from http://www.primefaces.org/showcase/ui/multimedia/galleria.xhtml
Read its Documentation.
 */
package market.mymarket.cdibeans;

/**
 *
 * @author Zhen
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

// RequestScoped import is added
import javax.enterprise.context.RequestScoped;

// import javax.faces.bean.ManagedBean; Removed since it is being deprecated.

// The CDI Named bean management is preferred
import javax.inject.Named;

// @ManagedBean Removed since it is being deprecated.
/*
-----------------------------------------------------------------------
JSF Managed Beans annotated as @ManagedBean from javax.faces.bean
is in the process of being deprecated in favor of CDI Beans. Therefore, 
you should use @Named from javax.inject package to designate a managed
bean as a JSF controller class. Contexts and Dependency Injection (CDI) 
beans annotated with @Named is the preferred approach, because CDI 
enables Java EE-wide dependency injection. 
CDI bean is a bean managed by the CDI container. 
-----------------------------------------------------------------------
 */
@Named(value = "imagesView")

// @RequestScoped annotation is added
/*
The @RequestScoped annotation preserves the values of the ImagesView
object's instance variables only in a single HTTP request-response cycle.
*/
@RequestScoped

public class ImagesView {

    private List<String> images;

    @PostConstruct
    public void init() {

        // ArrayList<String>() is changed to ArrayList<>()
        images = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            images.add("photo" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }
}
