package com.pwned.fireeye.demo.service;

import com.pwned.fireeye.demo.beans.PasteEntityResponse;

public interface IPasteService {

    PasteEntityResponse getPasteAccounts(String accountId);
}
