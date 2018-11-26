package fileManager;

import fileManager.model.FrameData;
import fileManager.model.Model;
import fileManager.view.DirectoryView;
import fileManager.view.View;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Model model = new FrameData("D://");
        View view = new DirectoryView(model);

    }
}
