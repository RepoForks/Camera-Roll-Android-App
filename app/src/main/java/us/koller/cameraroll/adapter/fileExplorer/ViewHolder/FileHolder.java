package us.koller.cameraroll.adapter.fileExplorer.ViewHolder;

import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import us.koller.cameraroll.R;
import us.koller.cameraroll.data.File_POJO;
import us.koller.cameraroll.data.StorageRoot;
import us.koller.cameraroll.ui.ThemeableActivity;
import us.koller.cameraroll.util.MediaType;

public class FileHolder extends RecyclerView.ViewHolder {

    public FileHolder(View itemView) {
        super(itemView);
    }

    public void setFile(File_POJO file) {
        ImageView folderIndicator = (ImageView) itemView.findViewById(R.id.folder_indicator);
        if (file instanceof StorageRoot) {
            if (file.getName().equals(itemView.getContext().getString(R.string.storage))) {
                folderIndicator.setImageResource(R.drawable.ic_smartphone_white_24dp);
            } else {
                folderIndicator.setImageResource(R.drawable.ic_sd_storage_white_24dp);
            }
        } else if (!file.isMedia) {
            if (new File(file.getPath()).isFile()) {
                folderIndicator.setImageResource(R.drawable.ic_insert_drive_file_white_24dp);
            } else {
                folderIndicator.setImageResource(R.drawable.ic_folder_white_24dp);
            }
        } else if (MediaType.isVideo(folderIndicator.getContext(), file.getPath())) {
            folderIndicator.setImageResource(R.drawable.ic_videocam_white_24dp);
        } else {
            folderIndicator.setImageResource(R.drawable.ic_photo_white_24dp);
        }

        TextView textView = (TextView) itemView.findViewById(R.id.text);
        textView.setText(file.getName());
    }

    public void setSelected(boolean selected) {
        itemView.setBackgroundColor(
                ContextCompat.getColor(itemView.getContext(),
                        selected ? R.color.colorAccent_translucent :
                                android.R.color.transparent));

        ThemeableActivity.ColorManager colorManager = ThemeableActivity.getColorManager();
        int color = colorManager.getColor(selected ?
                ThemeableActivity.ColorManager.ACCENT_TEXT_COLOR :
                ThemeableActivity.ColorManager.TEXT_COLOR_SEC);

        TextView textView = (TextView) itemView.findViewById(R.id.text);
        textView.setTextColor(color);

        ImageView folderIndicator = (ImageView)
                itemView.findViewById(R.id.folder_indicator);
        folderIndicator.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}
