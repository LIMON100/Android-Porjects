package example.android.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view){
        quantity = quantity - 1;
        if(quantity < 0){
            quantity = 0;
            display(quantity);
        }
        display(quantity);
    }

    public void submitOrder(View view) {
        EditText edt = (EditText) findViewById(R.id.edit_name);
        String n = edt.getText().toString();

        CheckBox whipedbox = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasClickd = whipedbox.isChecked();

        CheckBox chocbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean chasClickd = chocbox.isChecked();

        int price  = calculatePrice(hasClickd , chasClickd);
        //display(quantity);
        String priceMessage = orderSummary(n, price , hasClickd , chasClickd);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Coffe order app" + n);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private int calculatePrice(boolean wa , boolean ca){
        int basePrice = 5;

        if(wa){
            basePrice = basePrice + 1;
        }

        if(ca){
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }

    private String orderSummary(String n, int price , boolean hasClickd , boolean chasClickd){

        String priceMessage = "\nName: " + getString(R.string.order_summary_name);
        priceMessage = priceMessage + "\nQuantity " + quantity;
        priceMessage = priceMessage + "\nAdd whipped cream " + hasClickd;
        priceMessage = priceMessage + "\nAdd Chocolate " + chasClickd;
        priceMessage = priceMessage + "\nTotal $: " + price;
        priceMessage = priceMessage + "\n" + getString(R.string.thank_you);
        return priceMessage;

//        String priceMessage = getString(R.string.order_summary_name , n);
//        priceMessage = priceMessage + "\n" + getString(R.string.order_summary_whipped_cream , hasClickd);
//        priceMessage = priceMessage + "\n" + getString(R.string.order_summary_chocolate , chasClickd);
//        priceMessage = priceMessage + "\n" + getString(R.string.order_summary_quantity , quantity);
//        priceMessage = priceMessage + "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
//        priceMessage = priceMessage + "\n" + getString(R.string.thank_you);
//        return priceMessage;

        //TRANSLATE XML
        //android:text="@string/order_summary_whipped_cream", android:text="@string/order_summary_chocolate" , android:hint="@string/order_summary_name"


    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}