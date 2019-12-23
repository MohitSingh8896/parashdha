package project.namramuni.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import project.namramuni.Activity.BhaktilistActivity;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Holder.DevinityViewHolder;
import project.namramuni.Listener.MediaPlayers;
import project.namramuni.R;
import project.namramuni.UIDesign.BitmapTransform;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.DivinityList;

import static project.namramuni.Activity.MainActivity.audioPlayer;
import static project.namramuni.Activity.MainActivity.audioServiceBinder;
import static project.namramuni.background.AudioService.mantapoint;
import static project.namramuni.background.AudioService.shilpasongcount;
import static project.namramuni.background.AudioService.shilpasongid;
import static project.namramuni.background.AudioServiceBinder.firsttimesongs;

public class DivinityAdapter extends RecyclerView.Adapter<DevinityViewHolder> {
    Context context;
    List<DivinityList> list;
    String type;
    int c = 1;
    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;
    Boolean isAudPlay;
    public DivinityAdapter(Context context, List<DivinityList> imageId1, String type) {
        this.context = context;
        this.type = type;
        this.list = imageId1;
    }
    @Override
    public DevinityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_devinity_list, parent, false);
        return new DevinityViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DevinityViewHolder holder, int position) {
        try {
            SetFont.setFont(holder.textTitle, context, SetFont.bold);
            SetFont.setFont(holder.textSubTitle, context, SetFont.bold);
            Picasso.with(context).load(list.get(position).getCover_image()).into(holder.ImageView);
            if (shilpasongid.equals(list.get(position).getId())) {
                holder.audioPlay.setVisibility(View.VISIBLE);
            } else {
                holder.audioPlay.setVisibility(View.GONE);
            }
            if (list.get(position).getCover_image() != null) {
                holder.ImageView.setVisibility(View.GONE);
                holder.progressBar.setVisibility(View.VISIBLE);
                Picasso.with(context).load(list.get(position).getCover_image()).transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT)).into(holder.ImageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                        holder.ImageView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {
                        holder.ImageView.setVisibility(View.VISIBLE);
                        holder.progressBar.setVisibility(View.GONE);
                        Picasso.with(context).load(R.drawable.logo).into(holder.ImageView);
                    }
                });
            } else {
                holder.progressBar.setVisibility(View.GONE);
                holder.ImageView.setVisibility(View.VISIBLE);
                Picasso.with(context).load(R.drawable.logo).into(holder.ImageView);
            }
            holder.textTitle.setText(list.get(position).getTitle());
            holder.textSubTitle.setText(list.get(position).getCategory_name());
            if (type.equals("Meditation")) {
                holder.btnMeditation.setText("Meditate Now");
                holder.clickSongs.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        try {
                            firsttimesongs = "stop";
                            ((MainActivity)context).musicView("1", -1,list.get(position).getCover_image(),
                                    list.get(position).getTitle(), list.get(position).getLink(), list.get(position).getId());
                            shilpasongid = list.get(position).getId();
                            notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                holder.btnMeditation.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        try {
                            firsttimesongs = "stop";
                            ((MainActivity)context).musicView("1", -1,list.get(position).getCover_image(), list.get(position).getTitle(), list.get(position).getLink(), list.get(position).getId());
                            shilpasongid = list.get(position).getId();
                            notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (type.equals("Mantra")) {
                holder.clickSongs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getDialog(context, position, list.get(position));
                    }
                });
                holder.btnMeditation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getDialog(context, position, list.get(position));
                    }
                });
                if (mantapoint == position) {
                    holder.textCount.setVisibility(View.VISIBLE);
                    holder.textCount.setText("Total mantra count: " + shilpasongcount);
                    holder.btnMeditation.setText("Total played: " + String.valueOf(audioServiceBinder.CCC));
                    try {
                        /*audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                if (c == Integer.parseInt(shilpasongcount)) {
                                    holder.btnMeditation.setText("Chant Now");
                                    holder.textCount.setVisibility(View.GONE);
                                    holder.audioPlay.setVisibility(View.GONE);
                                } else {
                                    audioServiceBinder.CCC++;
                                    notifyDataSetChanged();
                                }
                            }
                        });*/
                    } catch (Exception e) {
                        Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    holder.btnMeditation.setText("Chant Now");
                    holder.textCount.setVisibility(View.GONE);
                }
            } else if (type.equals("Prathana")) {
                holder.btnMeditation.setText("Pray Now");
                holder.clickSongs.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        try {
                            mantapoint = -1;
                            firsttimesongs = "stop";
                            ((MainActivity)context).musicView("1", -1,list.get(position).getCover_image(), list.get(position).getTitle(), list.get(position).getLink(), list.get(position).getId());
//                            MediaPlayers.getMusic(list.get(position).getLink());
                            shilpasongid = list.get(position).getId();
                            notifyDataSetChanged();
                        } catch (Exception e){
                            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                holder.btnMeditation.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        try {
                            mantapoint = -1;
                            firsttimesongs = "stop";
                            ((MainActivity)context).musicView("1", -1,list.get(position).getCover_image(), list.get(position).getTitle(), list.get(position).getLink(), list.get(position).getId());
//                            MediaPlayers.getMusic(list.get(position).getLink());
                            shilpasongid = list.get(position).getId();
                            notifyDataSetChanged();
                        } catch (Exception e){
                            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (type.equals("bhakti")) {
                holder.btnMeditation.setText("Coming Soon");
                holder.clickSongs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, BhaktilistActivity.class)
                                .putExtra("id", list.get(position).getId())
                                .putExtra("img", list.get(position).getPlaylist_image())
                                .putExtra("name", list.get(position).getPlaylist_name())
                                .putExtra("type", "Bhakti"));
                    }
                });
                holder.btnMeditation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, BhaktilistActivity.class)
                                .putExtra("id", list.get(position).getId())
                                .putExtra("img", list.get(position).getPlaylist_image())
                                .putExtra("name", list.get(position).getPlaylist_name())
                                .putExtra("type", "Bhakti"));
                    }
                });
            } else {
                holder.btnMeditation.setText("Listen");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void getDialog(Context context, int position, DivinityList list) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.matracount);
        EditText text = (EditText) dialog.findViewById(R.id.edtText);
        Button button = (Button) dialog.findViewById(R.id.btnSubmit);
        Button button1 = (Button) dialog.findViewById(R.id.btnCancel);
        ImageView left_side = (ImageView) dialog.findViewById(R.id.left_side);
        ImageView right_side = (ImageView) dialog.findViewById(R.id.right_side);
        left_side.setVisibility(View.GONE);
        left_side.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(text.getText().toString()) - 1;
                if (a == 1) {
                    left_side.setVisibility(View.GONE);
                    text.setText(String.valueOf(a));
                } else {
                    left_side.setVisibility(View.VISIBLE);
                    text.setText(String.valueOf(a));
                }
            }
        });
        right_side.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left_side.setVisibility(View.VISIBLE);
                int a = Integer.parseInt(text.getText().toString()) + 1;
                text.setText(String.valueOf(a));
            }
        });
        ImageView imgClose = (ImageView) dialog.findViewById(R.id.imgclose);
        imgClose.setVisibility(View.GONE);
        dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                try {
                    mantapoint = position;
//                    count = text.getText().toString();
                    firsttimesongs = "stop";
                    shilpasongid = list.getId();
                    ((MainActivity)context).musicView(text.getText().toString(),position,list.getCover_image(), list.getTitle(), list.getLink(), list.getId());
                    notifyDataSetChanged();

//                    MediaPlayers.getMusic(list.getLink());
//                    songsId = list.getId();
//                    notifyDataSetChanged();
                    /*if (audioPlayer.isPlaying()) {
                        audioPlayer.stop();
                        audioPlayer.reset();
                        audioPlayer.release();
                        audioPlayer = null;
                        audioPlayer = new MediaPlayer();
                        songsId = "0";
                    }*/

                } catch (Exception e) {
                    Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}