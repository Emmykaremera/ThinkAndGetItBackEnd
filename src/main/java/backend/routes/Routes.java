package backend.routes;

public class Routes {

    public static final String REGISTER="/auth/register";

    public static final String LOGIN="/auth/login";

    public static final String USERS="/users";

    public static final String PROFILE="/users/profile";

    public static final String CURRENT_USER="/auth/me";

    public static final String UPDATE_PROFILE="/users/profile";

    public static final String CHANGE_PASSWORD="/users/change-password";

    public static final String ADDRESSES="/users/addresses";

    public static final String FORGOT_PASSWORD = "/auth/forgot-password";

    public static final String VERIFY_EMAIL = "/auth/verify-email";

    public static final String RESET_PASSWORD = "/auth/reset-password/";

    public static final String GET_CATEGORIES = "/categories";

    public static final String GET_CART="/cart";

    public static final String PRODUCTS = "/products";

    public static final String TRENDING_PRODUCTS = "/products/trending";

    public static final String FLASH_SALES_PRODUCTS = "/products/flash-sales";

    public static String productBySlug(String slug) {
        return "/products/" + slug;
    }

    public static String relatedProducts(String productId) {
        return "/products/" + productId + "/related";
    }

    public static String productById(String productId) {
        return "/products/" + productId;
    }

    public static String productImages(String productId) {
        return "/products/" + productId + "/images";
    }

}