interface Document {
    void createDocument();
}

class PdfDocument implements Document {

    @Override
    public void createDocument() {
        System.out.println("PDF Document Created");
    }
}

class WordDocument implements Document {

    @Override
    public void createDocument() {
        System.out.println("Word Document Created");
    }
}

class ExcelDocument implements Document {

    @Override
    public void createDocument() {
        System.out.println("Excel Document Created");
    }
}

abstract class DocumentFactory {

    abstract Document createDocument();

}

class PdfFactory extends DocumentFactory {

    @Override
    Document createDocument() {
        return new PdfDocument();
    }
}

class WordFactory extends DocumentFactory {

    @Override
    Document createDocument() {
        return new WordDocument();
    }
}

class ExcelFactory extends DocumentFactory {

    @Override
    Document createDocument() {
        return new ExcelDocument();
    }
}

public class FactoryMethodPatternExample {

    public static void main(String[] args) {

        DocumentFactory pdfFactory = new PdfFactory();
        Document pdf = pdfFactory.createDocument();
        pdf.createDocument();

        DocumentFactory wordFactory = new WordFactory();
        Document word = wordFactory.createDocument();
        word.createDocument();

        DocumentFactory excelFactory = new ExcelFactory();
        Document excel = excelFactory.createDocument();
        excel.createDocument();
    }
}