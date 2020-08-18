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
import com.idoctortech.idoctor.Classes.UtilityApp;
import com.idoctortech.idoctor.Model.AllChatsModel;
import com.idoctortech.idoctor.Model.ChatModel;
import com.idoctortech.idoctor.Model.MemberModel;
import com.idoctortech.idoctor.Model.ResultAPIModel;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.Utils.DateHandler;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


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

    List<ChatModel> objectsModelList;
    AllChatsModel allObjectModel;
    //    DataFetcherCallBack dataFetcherCallBack;
    MemberModel user;

    public ChatsAdapter(Activity activity, RecyclerView rv, List<ChatModel> objectsList, AllChatsModel allModel) {

        this.activity = activity;
        this.objectsModelList = objectsList;
        this.allObjectModel = allModel;
//        this.dataFetcherCallBack = callBack;

        user = UtilityApp.getUserData();

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
            View view = LayoutInflater.from(activity).inflate(R.layout.row_chat, viewGroup, false);

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

                ChatModel chatModel = objectsModelList.get(position);

                productsHolder.usernameTxt.setText(chatModel.getFriendName(user.id));
                productsHolder.descTxt.setText(chatModel.lastMsg);
                String time = DateHandler.FormatDate4(chatModel.updatedAt, "yyyy-MM-dd HH:mm", "hh:mm aa", null);
                productsHolder.dateTxt.setText(time);

//                Glide.with(activity)
//                        .asBitmap()
//                        .load(chatModel.getFriendLogo(user.id))
//                        .placeholder(R.drawable.error_logo)
//                        .override(200, 200)
//                        .into(productsHolder.userImg);

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
        private ImageView userImg;
        private TextView usernameTxt;
        private TextView descTxt;
        private TextView dateTxt;
        private TextView counterTxt;
        private TextView dividerLY;


        public ProductsHolder(View itemView) {
            super(itemView);

            rowLY = itemView.findViewById(R.id.rowLY);
            userImg = itemView.findViewById(R.id.userImg);
            usernameTxt = itemView.findViewById(R.id.usernameTxt);
            descTxt = itemView.findViewById(R.id.descTxt);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            counterTxt = itemView.findViewById(R.id.counterTxt);
            dividerLY = itemView.findViewById(R.id.dividerLY);


            itemView.setOnClickListener(view -> {

                ChatModel chatModel = objectsModelList.get(getAdapterPosition());

//                Intent intent = new Intent(activity, ChatDetailsActivity.class);
//                intent.putExtra(Constants.KEY_CHAT_ID, chatModel.getFriendId(user.id));
//                intent.putExtra(Constants.KEY_USER_NAME, chatModel.getFriendName(user.id));
//                intent.putExtra(Constants.KEY_AVATAR_URL, chatModel.getFriendLogo(user.id));
//                activity.startActivity(intent);

            });


        }

    }


    public ChatsAdapter getAdapter() {
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
                ResultAPIModel<AllChatsModel> resultAPIModel = (ResultAPIModel<AllChatsModel>) obj;
                allObjectModel = resultAPIModel.data;

                List<ChatModel> moreData = allObjectModel.items;
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

//        dataFeacher.getChats(newPage, false);


    }

}



