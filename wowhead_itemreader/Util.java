/*
 * Copyright (C) 2011 Thedeath<www.fseek.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package wowhead_itemreader;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Util
{
    public static String escapeSQL(String string)
    {
        if(string == null)return "";
        String replaceAll = string.replaceAll("'", "''");
        return replaceAll;
    }
    
    public static File cleanMainPath()
    {
        try
        {
            String path = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            File mainFileT = new File(decodedPath);
            String absolutePath = null;
            try
            {
                absolutePath = mainFileT.getCanonicalPath();
                if (absolutePath.contains(".jar"))
                {
                    int index = absolutePath.lastIndexOf(File.separator);
                    absolutePath = absolutePath.substring(0, index);
                }
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(new JFrame()),ex.getMessage());
                System.exit(1);
            }
            return new File(absolutePath);
        } 
        catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        catch(Exception ex)
        {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            return new File(".");
        }
        return new File(".");
    }
}
