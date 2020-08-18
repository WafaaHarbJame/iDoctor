package com.idoctortech.idoctor.Adapter;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.idoctortech.idoctor.ApiHandler.DataFeacher;
import com.idoctortech.idoctor.Classes.OnLoadMoreListener;
import com.idoctortech.idoctor.Classes.UtilityApp;
import com.idoctortech.idoctor.Model.AllMessagesModel;
import com.idoctortech.idoctor.Model.MemberModel;
import com.idoctortech.idoctor.Model.MessageModel;
import com.idoctortech.idoctor.Model.ResultAPIModel;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.Utils.DateHandler;

import java.util.List;


public class ConversationMessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    LayoutInflater layoutInflater;
    View view;
    OnLoadMoreListener mOnLoadMoreListener;
    private final int VIEW_TYPE_MY_MESSAGE = 0;
    private final int VIEW_TYPE_MY_IMAGE = 1;
    private final int VIEW_TYPE_FRIEND_MESSAGE = 2;
    private final int VIEW_TYPE_FRIEND_IMAGE = 3;
    private final int VIEW_TYPE_LOADING = 4;

    private boolean isLoading;
    private int visibleThreshold = 10;
    private int lastVisibleItem, totalItemCount;

    boolean show_loading = true;
    int nextPage = 2;
    RecyclerView rv;
    private Activity activity;


    final String TEXT_MESSAGE = "text";
    final String IMAGE_MESSAGE = "image";

    MemberModel user;

    List<MessageModel> objectsModelList;
    AllMessagesModel allObjectModel;
    int chatId;
    String friendAvatar;

    public ConversationMessagesAdapter(Activity activity, RecyclerView rv, List<MessageModel> objectsModelList
            , AllMessagesModel allMessagesModel, int chatId, String friendAvatar) {

        this.activity = activity;
        this.objectsModelList = objectsModelList;
        this.rv = rv;
        this.allObjectModel = allMessagesModel;
        this.chatId = chatId;
        this.friendAvatar = friendAvatar;

//        isOwner = UtilityApp.getUserType().equals(Constants.PLACE_OWNER);

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
        if (viewType == VIEW_TYPE_MY_MESSAGE) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_conversation_message_my, viewGroup, false);
            return new MyMessageViewHolder(view);
        } /*else if (viewType == VIEW_TYPE_MY_IMAGE) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_conversation_image_my, viewGroup, false);
            return new MyImageViewHolder(view);
        }*/ else if (viewType == VIEW_TYPE_FRIEND_MESSAGE) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_conversation_message_friend, viewGroup, false);
            return new FriendMessageViewHolder(view);
        }/* else if (viewType == VIEW_TYPE_FRIEND_IMAGE) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_conversation_image_friend, viewGroup, false);
            return new FriendImageViewHolder(view);
        }*/ else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.row_loading, viewGroup, false);

            return new LoadingViewHolder(view);
        }

        return null;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyMessageViewHolder) {
            MyMessageViewHolder myMessageViewHolder = (MyMessageViewHolder) holder;

            MessageModel fmessageModel = objectsModelList.get(position);

            myMessageViewHolder.myMessageTxt.setText(fmessageModel.body);
            myMessageViewHolder.timeTxt.setText(DateHandler.FormatDate4(fmessageModel.createdAt, "yyyy-MM-dd HH:mm", "hh:mm aa", null));

        } else if (holder instanceof FriendMessageViewHolder) {

            FriendMessageViewHolder friendMessageViewHolder = (FriendMessageViewHolder) holder;

            MessageModel fmessageModel = objectsModelList.get(position);

            friendMessageViewHolder.friendMessageTxt.setText(fmessageModel.body);
            friendMessageViewHolder.timeTxt.setText(DateHandler.FormatDate4(fmessageModel.createdAt, "yyyy-MM-dd HH:mm", "hh:mm aa", null));

//            Glide.with(activity)
//                    .asBitmap()
//                    .load(friendAvatar)
//                    .placeholder(R.drawable.error_logo)
//                    .into(friendMessageViewHolder.userImg);

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

    @Override
    public int getItemViewType(int position) {

        if (objectsModelList != null && objectsModelList.get(position) == null) {
            return VIEW_TYPE_LOADING;
        } else {
            if (user.getId() == objectsModelList.get(position).senderId) {
                return VIEW_TYPE_MY_MESSAGE;
            } else {
                return VIEW_TYPE_FRIEND_MESSAGE;
            }

//            if (position == 1 || position == 3) {
//                return VIEW_TYPE_MY_MESSAGE;
//            } else {
//                return VIEW_TYPE_FRIEND_MESSAGE;
//            }

        }

    }

    class FriendMessageViewHolder extends RecyclerView.ViewHolder {

        ImageView userImg;
        TextView friendMessageTxt;
        private TextView timeTxt;

        public FriendMessageViewHolder(View itemView) {
            super(itemView);

            userImg = itemView.findViewById(R.id.userImg);
            friendMessageTxt = itemView.findViewById(R.id.friendMessageTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);

        }
    }


    class MyMessageViewHolder extends RecyclerView.ViewHolder {


        TextView myMessageTxt;
        private TextView timeTxt;


        public MyMessageViewHolder(View itemView) {
            super(itemView);

            myMessageTxt = itemView.findViewById(R.id.myMessageTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);

        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar1);
        }

    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void setOnloadListener() {

        getAdapter().setOnLoadMoreListener(() -> {

//                System.out.println("Log load prev items");

            new Handler().post(() -> {
                int pos = 0;
                objectsModelList.add(pos, null);
                notifyItemInserted(pos/*objectsModelList.size() - 1*/);
            });

            //Load more dataModel for reyclerview

            new Handler().postDelayed(() -> {
//                        System.out.println("Log load new items");

                //Load dataModel
                LoadData(nextPage);

            }, 3000);
        });

    }

    public void LoadData(int newPage) {

        DataFeacher dataFeacher = new DataFeacher(activity, (obj, func, IsSuccess) -> {

            if (IsSuccess) {
                objectsModelList.remove(0);
                notifyItemRemoved(0);

                int pos = 0;
                ResultAPIModel<AllMessagesModel> resultAPIModel = (ResultAPIModel<AllMessagesModel>) obj;
                allObjectModel = resultAPIModel.data;

                List<MessageModel> moreData = allObjectModel.items;
//                    Collections.reverse(moreData);
                objectsModelList.addAll(pos, moreData);
                nextPage++;
                notifyItemRangeInserted(pos, moreData.size());
//                    notifyDataSetChanged();
            } else {
                objectsModelList.remove(0);
                notifyItemRemoved(0);
            }
            setLoaded();
        });

//        if (UtilityApp.getUserType().equals(Constants.PLACE_OWNER))
        dataFeacher.getChatMessages(chatId, newPage, false);
//        else
//            dataFeacher.getClientChatDetails(chatId, newPage, false);


    }

    public void showLoadMore() {

        if (allObjectModel != null)
            show_loading = allObjectModel.nextPageUrl != null;
        else
            show_loading = false;
    }

    public ConversationMessagesAdapter getAdapter() {
        return this;
    }

}

