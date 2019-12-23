package project.namramuni.Fragement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.shockwave.pdfium.PdfDocument;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Activity.PDFViewerActivity;
import project.namramuni.Adapter.ReadAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Readlist;
import project.namramuni.ViewModels.List.SubReadList;
import project.namramuni.ViewModels.Main.GurudevReadlistResponse;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static project.namramuni.Application.masterContext;

public class FragmentRead extends Fragment implements OnPageChangeListener, OnLoadCompleteListener {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rcGurudevList)
    RecyclerView rcGurudevList;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    @BindView(R.id.rlvBook)
    RelativeLayout rlvBook;
    @BindView(R.id.imgBook)
    ImageView imgBook;
    @BindView(R.id.txtBook)
    TextView txtBook;
    @BindView(R.id.rlvMagzin)
    RelativeLayout rlvMagzin;
    @BindView(R.id.txtMagzin)
    TextView txtMagzin;
    @BindView(R.id.imgMagzin)
    ImageView imgMagzin;
    @BindView(R.id.rlvMagzin1)
    RelativeLayout rlvMagzin1;
    @BindView(R.id.imgMagzin1)
    ImageView imgMagzin1;
    @BindView(R.id.txtMagzin1)
    TextView txtMagzin1;
    public static RelativeLayout lnr3;
    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.imgclose)
    ImageView imgclose;
    String pdfFileName;
    Integer pageNumber = 0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    List<SubReadList> filelist;
    ReadAdapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentgurudev, null);
        ButterKnife.bind(this, view);
        lnr3 = view.findViewById(R.id.lnr3);
        lnr1.setVisibility(View.VISIBLE);
        lnr3.setVisibility(View.GONE);
        filelist = new ArrayList<>();
        SetFont.setFont(txtBook, getContext(), SetFont.bold);
        SetFont.setFont(txtMagzin, getContext(), SetFont.bold);
        SetFont.setFont(txtMagzin1, getContext(), SetFont.bold);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        rcGurudevList.setHasFixedSize(true);
        rlvBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab("book", view);
            }
        });
        rlvMagzin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab("jain_kranti", view);
            }
        });
        rlvMagzin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab("look_learn", view);
            }
        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            String response = bundle.getString("response");
//            if (response.equals("set"))
//                getList(headlist.get(Integer.parseInt(bundle.getString("position"))).getId());
        } else {
        }
        rcGurudevList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcGurudevList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                lnr3.setVisibility(View.VISIBLE);
                displayFromAsset(filelist.get(position));
            }
        }));
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnr3.setVisibility(View.GONE);
            }
        });
        setTab("book", view);
        return view;
    }

    private void displayFromAsset(SubReadList assetFileName) {
        startActivity(new Intent(getContext(), PDFViewerActivity.class)
                .putExtra("pdf", assetFileName.getFile_url())
        );
       /* pageNumber = 0;
        pdfFileName = assetFileName;
        pdfView.fromAsset(pdfFileName)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(getContext()))
                .load();*/
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getList(String type, View view) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<GurudevReadlistResponse> call = ApiClientMain.getApiClient().getReadList(type);
            call.enqueue(new Callback<GurudevReadlistResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<GurudevReadlistResponse> call, Response<GurudevReadlistResponse> response) {
                    if (response != null && response.body() != null) {
                        GurudevReadlistResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            filelist = userResponse.getFiles();
                            if (filelist != null) {
                                setList(filelist);
                            } else {
                                filelist = new ArrayList<>();
                                setList(filelist);
                            }
                            Loading.Visibility(getContext(), view, 1);
                        } else {
                            Loading.Visibility(getContext(), view, 1);
                        }
                    } else {
                        Loading.Visibility(getContext(), view, 2);
                    }
                }
                @Override
                public void onFailure(Call<GurudevReadlistResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }

//    private void getList(View view, String id) {
//        Loading.Visibility(view);
//        if (NetworkInfo.isNetworkAvailable(getContext())) {
//            Call<GurudevReadlistResponse> call = ApiClientMain.getApiClient().getReadList(id);
//            call.enqueue(new Callback<GurudevReadlistResponse>() {
//                @RequiresApi(api = Build.VERSION_CODES.M)
//                @Override
//                public void onResponse(Call<GurudevReadlistResponse> call, Response<GurudevReadlistResponse> response) {
//                    if (response != null && response.body() != null) {
//                        GurudevReadlistResponse userResponse = response.body();
//                        if (userResponse.getStatus().equals("success")) {
//                            lnr1.setVisibility(View.VISIBLE);
//                            headlist = userResponse.getHeading();
//                            Loading.Visibility(getContext(), view, 1);
//                            setTab("book", view);
//                        } else {
//                            lnr1.setVisibility(View.GONE);
//                            Loading.Visibility(getContext(), view, 2);
//                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        lnr1.setVisibility(View.GONE);
//                        Loading.Visibility(getContext(), view, 2);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<GurudevReadlistResponse> call, Throwable t) {
//                    lnr1.setVisibility(View.GONE);
//                    Loading.Visibility(getContext(), view, 500);
//                }
//            });
//        } else {
//            lnr1.setVisibility(View.GONE);
//            Loading.Visibility(getContext(), view, 404);
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTab(String s, View view) {
        setSelection(s);
        getList(s, view);
    }

    private void setList(List<SubReadList> filter) {
        adapter = new ReadAdapter(getContext(), filter);
        rcGurudevList.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setSelection(String s) {
        int heading = ContextCompat.getColor(masterContext, R.color.heading);
        int heading1 = ContextCompat.getColor(masterContext, R.color.white);
        if (s.equals("book")) {
            rlvBook.setBackgroundResource(R.drawable.read1);
            imgBook.setColorFilter(heading1);
            txtBook.setTextColor(heading1);
            rlvMagzin.setBackgroundResource(R.drawable.read2);
            imgMagzin.setColorFilter(heading);
            txtMagzin.setTextColor(heading);
            rlvMagzin1.setBackgroundResource(R.drawable.read2);
            imgMagzin1.setColorFilter(heading);
            txtMagzin1.setTextColor(heading);
        } else if (s.equals("jain_kranti")) {
            rlvBook.setBackgroundResource(R.drawable.read2);
            rlvMagzin.setBackgroundResource(R.drawable.read1);
            rlvMagzin1.setBackgroundResource(R.drawable.read2);
            imgBook.setColorFilter(heading);
            txtBook.setTextColor(heading);
            imgMagzin.setColorFilter(heading1);
            txtMagzin.setTextColor(heading1);
            imgMagzin1.setColorFilter(heading);
            txtMagzin1.setTextColor(heading);
        } else if (s.equals("look_learn")) {
            rlvBook.setBackgroundResource(R.drawable.read2);
            rlvMagzin.setBackgroundResource(R.drawable.read2);
            rlvMagzin1.setBackgroundResource(R.drawable.read1);
            imgBook.setColorFilter(heading);
            txtBook.setTextColor(heading);
            imgMagzin.setColorFilter(heading);
            txtMagzin.setTextColor(heading);
            imgMagzin1.setColorFilter(heading1);
            txtMagzin1.setTextColor(heading1);
        }
    }
}