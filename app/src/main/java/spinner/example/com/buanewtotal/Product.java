package spinner.example.com.buanewtotal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

   @SerializedName("id")
   public int id;

   // @SerializedName("username")
   // public String username;

    @SerializedName("name")
    public String name;

    //@SerializedName("address")
  //  public String address;


    @SerializedName("area")
    public String area;
   /* @SerializedName("qty")
    public int qty;

    @SerializedName("price")
    public float price;*/

  //  @SerializedName("age")
  //  public int age;

    @SerializedName("age")
    public String age;


    //@SerializedName("phone")
   // public int phone;


    @SerializedName("contact")
    public String contact;

    @SerializedName("salary")
    public String salary;

    @SerializedName("ref")
    public String ref;

    @SerializedName("wash")
    public String wash;


    @SerializedName("clean")
    public String clean;

    @SerializedName("cook")
   public String cook;



  //  @SerializedName("password")
  //  public String password;

   @SerializedName("image_url")
    public String image_url;
}
