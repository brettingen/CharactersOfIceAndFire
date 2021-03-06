package com.arbrettingen.charactersoficeandfire;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ASOIAFCharacterAdapter populates the Main ListView with relevant row views containing view data
 * based on the given ArrayList of ASOIAFCharacter objects.
 */

public class ASOIAFCharacterAdapter extends ArrayAdapter {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<ASOIAFCharacter> mData = null;
    String[] houseRegions;

    public ASOIAFCharacterAdapter(Context context, int resource, ArrayList<ASOIAFCharacter> data, String[] houseRegions) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
        this.houseRegions = houseRegions;
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View row, @NonNull ViewGroup parent) {
        CharHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new CharHolder();

            holder.largeText = (TextView) row.findViewById(R.id.list_item_largeTextView);
            holder.smallText = (TextView) row.findViewById(R.id.list_item_smallTextView);
            holder.imageView = (ImageView) row.findViewById(R.id.list_item_sigil);

            row.setTag(holder);
        } else {
            holder = (CharHolder) row.getTag();
        }

        holder.largeText.setText(mData.get(position).getmName());

        if (mData.get(position).getmAliases().size() > 0) {
            holder.smallText.setText(mData.get(position).getmAliases().get(0));
        } else {
            holder.smallText.setText("");
        }

        //Pick icon house image to display based on the region of the given latest house allegiance
        String mainAllegiance = "unaligned";
        if (mData.get(position).getmAllegiances().size() > 0) {
            for (String houseUrl : mData.get(position).getmAllegiances()) {

                int housePosition = Integer.parseInt(houseUrl.substring(45)) - 1;

                if (houseRegions[housePosition].equals("The Vale")) {
                    mainAllegiance = "arryn";
                }
                if (houseRegions[housePosition].equals("The Stormlands")) {
                    mainAllegiance = "baratheon";
                }
                if (houseRegions[housePosition].equals("Iron Islands")) {
                    mainAllegiance = "greyjoy";
                }
                if (houseRegions[housePosition].equals("The Westerlands")) {
                    mainAllegiance = "lannister";
                }
                if (houseRegions[housePosition].equals("Dorne")) {
                    mainAllegiance = "martell";
                }
                if (houseRegions[housePosition].equals("The North")) {
                    mainAllegiance = "stark";
                }
                if (houseRegions[housePosition].equals("The Crownlands")) {
                    mainAllegiance = "targaryen";
                }
                if (houseRegions[housePosition].equals("The Riverlands")) {
                    mainAllegiance = "tully";
                }
                if (houseRegions[housePosition].equals("The Reach")) {
                    mainAllegiance = "tyrell";
                }
                if (houseRegions[housePosition].equals("The Neck")) {
                    mainAllegiance = "tully";
                }

            }
        }

        int resID = mContext.getResources().getIdentifier(mainAllegiance, "drawable",
                mContext.getPackageName());
        holder.imageView.setImageResource(resID);

        return row;
    }


    private static class CharHolder {

        TextView largeText;
        TextView smallText;
        ImageView imageView;

    }

}
