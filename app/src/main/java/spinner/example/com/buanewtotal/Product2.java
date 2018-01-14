package spinner.example.com.buanewtotal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by User on 5/9/2016.
 */
public class Product2 implements Serializable {

    @SerializedName("id")
    public int id;

    // @SerializedName("username")
    // public String username;

    @SerializedName("name")
    public String name;

    @SerializedName("area")
    public String address;

   /* @SerializedName("qty")
    public int qty;

    @SerializedName("price")
    public float price;*/

    @SerializedName("age")
    public String age;

    //@SerializedName("phone")
    // public int phone;

    @SerializedName("contact")
    public String phone;

    @SerializedName("cook")
    public String salary;

    @SerializedName("clean")
    public String clean;

    @SerializedName("wash")
    public String wash;

    @SerializedName("ref")
    public String ref;

    //  @SerializedName("password")
    //  public String password;

    @SerializedName("image_url")
    public String image_url;
}
