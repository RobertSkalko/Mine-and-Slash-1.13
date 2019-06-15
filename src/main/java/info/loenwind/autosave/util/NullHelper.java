package info.loenwind.autosave.util;

import javax.annotation.Nullable;

// Copied from EnderCore, with some methods removed
public final class NullHelper {

  private NullHelper() {
  }

  
  public final static <P> P notnull(@Nullable P o, String message) {
    if (o == null) {
      throw new NullPointerException(
          "Houston we have a problem: '" + message + "'. " + "Please report that on our bugtracker unless you are using some old version. Thank you.");
    }
    return o;
  }

  
  public final static <P> P notnullJ(@Nullable P o, String message) {
    if (o == null) {
      throw new NullPointerException(
          "There was a problem with Java: The call '" + message + "' returned null even though it should not be able to do that. Is your Java broken?");
    }
    return o;
  }

  
  public final static <P> P notnullM(@Nullable P o, String message) {
    if (o == null) {
      throw new NullPointerException("There was a problem with Minecraft: The call '" + message
          + "' returned null even though it should not be able to do that. Is your Minecraft broken? Did some other mod break it?");
    }
    return o;
  }

  
  public final static <P> P notnullF(@Nullable P o, String message) {
    if (o == null) {
      throw new NullPointerException("There was a problem with Forge: The call '" + message
          + "' returned null even though it should not be able to do that. Is your Forge broken? Did some other mod break it?");
    }
    return o;
  }

}
