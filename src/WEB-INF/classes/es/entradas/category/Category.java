/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.entradas.category;

import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
public class Category {

    private CategoryDAO categoryDAO = new CategoryDAO();
    private CategoryData categoryData = new CategoryData();
    private Map categoriMap = new HashMap();

    public static void setCategoryFacesContext(Category category) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("category", category);
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public CategoryData getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
    }

    public Map getCategoriMap() {
        if(categoriMap.isEmpty()){
            categoriMap = categoryDAO.getCategoryMap();
        }
        return categoriMap;
    }

    public void setCategoriMap(Map categoriMap) {
        this.categoriMap = categoriMap;
    }

}
