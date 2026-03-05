package com.example.Java_Jpa.service;

import com.example.Java_Jpa.domain.Category;
import com.example.Java_Jpa.domain.Hardware;
import com.example.Java_Jpa.domain.SearchHardware;
import com.example.Java_Jpa.dto.HardwareDTO;
import com.example.Java_Jpa.dto.SearchHardwareDTO;
import com.example.Java_Jpa.repository.SpringDataCategoryRepository;
import com.example.Java_Jpa.repository.SpringDataJpaHardwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HardwareServiceImpl implements HardwareService {
    //private ArticleRepository articleRepository;
    private SpringDataJpaHardwareRepository hardwareRepository;
    private SpringDataCategoryRepository categoryRepository;

    @Override
    public List<HardwareDTO> getAllHardware() {
        return hardwareRepository.findAll().stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();
        /*
        return articleRepository.getAllArticles().stream()
                .map(this::convertArticleToArticleDTO)
                .toList();

         */
    }

    @Override
    public List<HardwareDTO> getHardwareByName(String hardwareName) {
        return hardwareRepository.findByName(hardwareName).stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();
        /*
        return articleRepository.getArticlesByName(articleName).stream()
                .map(this::convertArticleToArticleDTO)
                .toList();

         */
    }

    @Override
    public HardwareDTO saveNewHardware(HardwareDTO hardware) {
        return convertHardwareToHardwareDTO(hardwareRepository.save(convertHardwareDtoToHardware(hardware)));
        //return convertArticleToArticleDTO(articleRepository.saveNewArticle(convertArticleDtoToArticle(article)));
    }

    @Override
    public List<HardwareDTO> filterByParameters(SearchHardwareDTO searchHardwareDTO) {
        Hardware hardware = new Hardware();
        hardware.setName(searchHardwareDTO.getHardwareName());
        hardware.setCode(searchHardwareDTO.getHardwareCode());
        hardware.setPrice(searchHardwareDTO.getLowerPrice() != null ? searchHardwareDTO.getLowerPrice() : null);
        hardware.setCategory(categoryRepository.findByName(searchHardwareDTO.getCategoryName()));

        Specification<Hardware> spec = Specification.where((Specification<Hardware>) null);

        if (searchHardwareDTO.getHardwareName() != null && !searchHardwareDTO.getHardwareName().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), searchHardwareDTO.getHardwareName()));
        }

        if (searchHardwareDTO.getHardwareCode() != null && !searchHardwareDTO.getHardwareCode().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("description"), searchHardwareDTO.getHardwareCode()));
        }

        if (searchHardwareDTO.getLowerPrice() != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), searchHardwareDTO.getLowerPrice()));
        }

        if (searchHardwareDTO.getUpperPrice() != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), searchHardwareDTO.getUpperPrice()));
        }

        if (searchHardwareDTO.getCategoryName() != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), searchHardwareDTO.getCategoryName()));
        }

        return hardwareRepository.findAll(spec).stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();

        /*
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.exact());

        if (searchArticleDTO.getLowerPrice() != null) {
            matcher = matcher.withMatcher("price",
                    ExampleMatcher.GenericPropertyMatchers.greaterThanOrEqualTo(searchArticleDTO.getLowerPrice()));
        }
        if (searchArticleDTO.getUpperPrice() != null) {
            matcher = matcher.withMatcher("price",
                    ExampleMatcher.GenericPropertyMatchers.lessThanOrEqualTo(searchArticleDTO.getLowerPrice()));
        }

        Example<Article> example = Example.of(article, matcher);
        return articleRepository.findAll(example).stream()
                .map(this::convertArticleToArticleDTO)
                .toList();

         */


        /*
        return articleRepository.filterByParameters(
                convertSearchArticleDtoToSearchArticle(searchArticleDTO))
                .stream().map(this::convertArticleToArticleDTO)
                .toList();

         */
    }

    @Override
    public Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id) {

        Optional<Hardware> hardwareToUpdate = hardwareRepository.findById(id);

        if (hardwareToUpdate.isPresent()) {
            Hardware hardware = hardwareToUpdate.get();
            hardware.setCategory(categoryRepository.findByName(hardwareDTO.getCategoryName()));
            hardware.setPrice(hardwareDTO.getHardwarePrice());
            hardware.setCode(hardwareDTO.getHardwareCode());
            hardware.setName(hardwareDTO.getHardwareName());
            Hardware updatedHardware = hardwareRepository.save(hardware);
            return Optional.of(convertHardwareToHardwareDTO(updatedHardware));
        }

        return Optional.empty();
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        return hardwareRepository.findById(id).isPresent();
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        if (hardwareByIdExists(id)) {
            hardwareRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private HardwareDTO convertHardwareToHardwareDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getName(),
                hardware.getCode(), hardware.getPrice(),hardware.getQuantity(),
                hardware.getCategory().getName());
    }

    private Hardware convertHardwareDtoToHardware(HardwareDTO hardwareDTO) {

        Hardware hardware = new Hardware();
        hardware.setName(hardwareDTO.getHardwareName());
        hardware.setCode(hardwareDTO.getHardwareCode());
        hardware.setPrice(hardwareDTO.getHardwarePrice());
        Category category = categoryRepository.findByName(hardwareDTO.getCategoryName());
        if (category == null) {
            throw new IllegalArgumentException("Category with name '" + hardwareDTO.getCategoryName() + "' not found.");

        }
        hardware.setCategory(category);
        return hardware;

        //        Integer latestId =
//                articleRepository.findAll().stream()
//                        .max((a1, a2) -> a1.getId().compareTo(a2.getId()))
//                        .get().getId();
//
//        return new Article(latestId + 1, articleDTO.getArticleName(),
//                articleDTO.getArticleDescription(), articleDTO.getArticlePrice(),
//                categoryRepository.findByName(articleDTO.getCategoryName()));
        //        return article;
    }


    private SearchHardware convertSearchArticleDtoToSearchArticle(SearchHardwareDTO searchHardwareDTO) {
        return new SearchHardware(
                searchHardwareDTO.getHardwareName(),
                searchHardwareDTO.getHardwareCode(),
                searchHardwareDTO.getLowerPrice(),
                searchHardwareDTO.getUpperPrice(),
                categoryRepository.findByName(searchHardwareDTO.getCategoryName()));
    }
}
