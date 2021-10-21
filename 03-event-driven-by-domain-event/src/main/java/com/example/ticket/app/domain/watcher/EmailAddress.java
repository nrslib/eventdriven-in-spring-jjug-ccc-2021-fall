package com.example.ticket.app.domain.watcher;

import com.example.ticket.appsupportstack.domain.StringValueObject;

import java.util.regex.Pattern;

public class EmailAddress extends StringValueObject {
    public EmailAddress(String address) {
        super(address);

        requireEmailAddressFormat(address);
    }

    private void requireEmailAddressFormat(String address) {
        var pattern = Pattern.compile("^[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]+(\\.[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]+)*@[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]+(\\.[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]+)*$");
        if(!pattern.matcher(address).matches()) {
            throw new IllegalArgumentException("Invalid email address.");
        }
    }
}
