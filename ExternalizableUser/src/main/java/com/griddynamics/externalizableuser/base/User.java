package com.griddynamics.externalizableuser.base;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class User implements Externalizable {

    private WebPage webPage = new WebPage(this);

    // bad code is for the sake of the task, do not do it in real life
    private boolean isActive = true;

    private boolean isAdmin = false;

    private boolean isModerator = true;

    private boolean isVIP = false;

    private boolean isMuted = true;

    private boolean isBanned = false;

    /**
     * The object implements the writeExternal method to save its contents
     * by calling the methods of DataOutput for its primitive values or
     * calling the writeObject method of ObjectOutput for objects, strings,
     * and arrays.
     *
     * @param out the stream to write the object to
     * @throws IOException Includes any I/O exceptions that may occur
     * @serialData Overriding methods should use this tag to describe
     * the data layout of this Externalizable object.
     * List the sequence of element types and, if possible,
     * relate the element to a public/protected field and/or
     * method of this Externalizable class.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeObject(webPage);
        out.writeBoolean(isActive);
        out.writeBoolean(isAdmin);
        out.writeBoolean(isModerator);
        out.writeBoolean(isVIP);
        out.writeBoolean(isMuted);
        out.writeBoolean(isBanned);
    }

    /**
     * The object implements the readExternal method to restore its
     * contents by calling the methods of DataInput for primitive
     * types and readObject for objects, strings and arrays.  The
     * readExternal method must read the values in the same sequence
     * and with the same types as were written by writeExternal.
     *
     * @param in the stream to read data from in order to restore the object
     * @throws IOException            if I/O errors occur
     * @throws ClassNotFoundException If the class for an object being
     *                                restored cannot be found.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        this.webPage = (WebPage) in.readObject();
        this.isActive = in.readBoolean();
        this.isAdmin = in.readBoolean();
        this.isModerator = in.readBoolean();
        this.isVIP = in.readBoolean();
        this.isMuted = in.readBoolean();
        this.isBanned = in.readBoolean();
    }

}
