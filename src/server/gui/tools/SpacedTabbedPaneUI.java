package server.gui.tools;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

//选项卡的风格设置
public class SpacedTabbedPaneUI extends BasicTabbedPaneUI {
    @Override
    protected LayoutManager createLayoutManager() {
        return new BasicTabbedPaneUI.TabbedPaneLayout() {
            @Override
            protected void calculateTabRects(int tabPlacement, int tabCount) {
                final int spacer = 1; // should be non-negative
                final int indent = 3;

                super.calculateTabRects(tabPlacement, tabCount);

                for (int i = 0; i < rects.length; i++) {
                    rects[i].x += i * spacer + indent;
                }
            }
        };
    }
}
