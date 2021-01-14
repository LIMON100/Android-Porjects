package example.android.findbooks;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    public BookAdapter(Activity context, ArrayList<Book> Books) {

        super(context, 0, Books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Book currentBook = getItem(position);
        Log.i(LOG_TAG, "Item position: " + position);

        TextView titleBookTextView = (TextView) listItemView.findViewById(R.id.book_title);
        TextView authorBookTextView = (TextView) listItemView.findViewById(R.id.author);
        ImageView coverImageView = (ImageView) listItemView.findViewById(R.id.cover_image);
        TextView priceBookTextView = (TextView) listItemView.findViewById(R.id.book_price);
        TextView languageCode = (TextView) listItemView.findViewById(R.id.country_code);
        TextView currencyCode = (TextView) listItemView.findViewById(R.id.currency_code);

        assert currentBook != null;
        titleBookTextView.setText(currentBook.getTitle());
        authorBookTextView.setText(currentBook.getAuthor());
        Picasso.with(getContext()).load(currentBook.getImageUrl()).into(coverImageView);
        priceBookTextView.setText(String.valueOf(formatPrice(currentBook.getPrice())));
        languageCode.setText(currentBook.getLanguage());
        currencyCode.setText(currentBook.getCurrency());

        Log.i(LOG_TAG, "ListView has been returned");
        return listItemView;
    }

    private String formatPrice(double price) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.00");
        return magnitudeFormat.format(price);
    }
}
