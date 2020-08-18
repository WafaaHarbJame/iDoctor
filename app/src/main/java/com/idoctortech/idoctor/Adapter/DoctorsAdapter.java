package com.idoctortech.idoctor.Adapter;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idoctortech.idoctor.ApiHandler.DataFeacher;
import com.idoctortech.idoctor.Classes.OnLoadMoreListener;
import com.idoctortech.idoctor.Model.AllPlacesModel;
import com.idoctortech.idoctor.Model.PlaceModel;
import com.idoctortech.idoctor.Model.ResultAPIModel;
import com.idoctortech.idoctor.Model.SearchModel;
import com.idoctortech.idoctor.R;

import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final Activity activity;

    LayoutInflater layoutInflater;
    View view;
    OnLoadMoreListener mOnLoadMoreListener;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    boolean show_loading = false;
    int nextPage = 2;

    List<PlaceModel> objectsModelList;
    AllPlacesModel allObjectModel;
    SearchModel searchModel;

    public DoctorsAdapter(Activity activity, RecyclerView rv, List<PlaceModel> objectsList, AllPlacesModel allModel, SearchModel searchModel) {

        this.activity = activity;
        this.objectsModelList = objectsList;
        this.allObjectModel = allModel;
        this.searchModel = searchModel;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rv.getLayoutManager();

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                showLoadMore();
                if (show_loading) {
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }

            }

        });
        setOnloadListener();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.row_doctor, viewGroup, false);

            return new ProductsHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.row_loading, viewGroup, false);

            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ProductsHolder) {
            final ProductsHolder productsHolder = (ProductsHolder) holder;

            if (objectsModelList != null) {

                PlaceModel placeModel = objectsModelList.get(position);

                productsHolder.nameTxt.setText(placeModel.title);
                productsHolder.descTxt.setText(placeModel.description);


                if (placeModel.cityName != null)
                    productsHolder.cityNameTxt.setText(placeModel.cityName);
                else
                    productsHolder.cityNameTxt.setText("-");


//                Glide.with(activity)
//                        .asBitmap()
//                        .load(placeModel.first_img)
//                        .placeholder(R.drawable.error_logo)
//                        .override(200, 200)
//                        .into(productsHolder.placeImg);

            }

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        if (objectsModelList != null)
            return objectsModelList.size();
        else
            return 0;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (objectsModelList != null)
            return objectsModelList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        else
            return VIEW_TYPE_ITEM;
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar1);
        }
    }

    class ProductsHolder extends RecyclerView.ViewHolder {

        private LinearLayout rowLY;
        private ImageView placeImg;
        private TextView nameTxt;
        private TextView descTxt;
        private TextView cityNameTxt;
        private TextView areaTxt;


        public ProductsHolder(View itemView) {
            super(itemView);

            rowLY = itemView.findViewById(R.id.rowLY);
            placeImg = itemView.findViewById(R.id.placeImg);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            descTxt = itemView.findViewById(R.id.descTxt);
            cityNameTxt = itemView.findViewById(R.id.cityNameTxt);
            areaTxt = itemView.findViewById(R.id.areaTxt);

            itemView.setOnClickListener(view -> {
//                PlaceModel placeModel = objectsModelList.get(getAdapterPosition());

//                Intent intent = new Intent(activity, PlaceDetailsActivity.class);
//                intent.putExtra(Constants.KEY_PLACE_ID, placeModel.id);
//                activity.startActivity(intent);

            });


        }

    }


    public DoctorsAdapter getAdapter() {
        return this;
    }

    public void setOnloadListener() {

        getAdapter().setOnLoadMoreListener(() -> {

//                System.out.println("Log load prev items");

            new Handler().post(() -> {
                objectsModelList.add(null);
                notifyItemInserted(objectsModelList.size() - 1);
            });

            //Load more dataModel for reyclerview

            new Handler().postDelayed(() -> {
//                        System.out.println("Log load new items");

                //Load dataModel
                LoadData(nextPage);

            }, 3000);
        });

    }

    public void showLoadMore() {

        if (allObjectModel != null)
            show_loading = allObjectModel.nextPageUrl != null;
        else
            show_loading = false;
    }

    /**
     * to load next page
     *
     * @param newPage
     */
    public void LoadData(int newPage) {

        DataFeacher dataFeacher = new DataFeacher(activity, (obj, func, IsSuccess) -> {

            if (IsSuccess) {
                objectsModelList.remove(objectsModelList.size() - 1);
                notifyItemRemoved(objectsModelList.size());

                int pos = objectsModelList.size();
                ResultAPIModel<AllPlacesModel> resultAPIModel = (ResultAPIModel<AllPlacesModel>) obj;
                allObjectModel = resultAPIModel.data;

                List<PlaceModel> moreData = allObjectModel.items;
                objectsModelList.addAll(moreData);
                nextPage++;
                notifyItemRangeInserted(pos, objectsModelList.size());
//                    notifyDataSetChanged();
            } else {
                objectsModelList.remove(objectsModelList.size() - 1);
                notifyItemRemoved(objectsModelList.size());
            }
            setLoaded();
        });

        dataFeacher.getPlaces(searchModel, newPage, false);


    }

}



