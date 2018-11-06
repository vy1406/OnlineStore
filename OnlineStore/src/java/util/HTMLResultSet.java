package util;

import java.sql.*;

public class HTMLResultSet {

    private ResultSet rs;

    public HTMLResultSet(ResultSet rs) {
        this.rs = rs;
    }
    
    @Override
    public String toString() { // can be called at most once
        StringBuffer out = new StringBuffer();
// Start a table to display the result set
        out.append("<TABLE BORDER=1 ALIGN=CENTER>\n");
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int numcols = rsmd.getColumnCount();
// Title the table with the result set's column labels
            out.append("<TR BGCOLOR=\"#FFAD00\">");
            for (int i = 1; i <= numcols; i++) {
                out.append("<TH>" + rsmd.getColumnLabel(i));
            }
            out.append("</TR>\n");
            while (rs.next()) {
                out.append("<TR>"); // start a new row
                for (int i = 1; i <= numcols; i++) {
                    out.append("<TD>"); // start a new data element
                    Object obj = rs.getObject(i);
                    if (obj != null) {
                        out.append(obj.toString());
                    } else {
                        out.append("&nbsp;");
                    }
                }
                out.append("</TR>\n");
            }
// End the table
            out.append("</TABLE>\n");
        } catch (SQLException e) {
            out.append("</TABLE><H1>ERROR:</H1> " + e.getMessage() + "\n");
        }
        return out.toString();
    }
}
