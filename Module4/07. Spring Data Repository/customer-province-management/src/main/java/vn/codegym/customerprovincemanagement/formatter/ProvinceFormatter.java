package vn.codegym.customerprovincemanagement.formatter;

import vn.codegym.customerprovincemanagement.model.Province;
import vn.codegym.customerprovincemanagement.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class ProvinceFormatter implements Formatter<Province> {

    private final IProvinceRepository provinceRepository;

    @Autowired
    public ProvinceFormatter(IProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Province parse(String text, Locale locale) {
        Optional<Province> provinceOptional = provinceRepository.findById(Long.parseLong(text));
        return provinceOptional.orElse(null);
    }

    @Override
    public String print(Province object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
